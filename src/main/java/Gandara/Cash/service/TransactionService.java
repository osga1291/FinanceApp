package Gandara.Cash.service;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Dao.transactionDao;
import Gandara.Cash.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    UserDao userData;
    @Autowired
    transactionDao transData;

    public Transaction addTrans(Long userId, Transaction transaction) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {

            Transaction newTrans = new Transaction();
            newTrans.setUser(user);
            //newTrans.setDate(transaction.getDate());
            newTrans.setAmount(transaction.getAmount());
            newTrans.setCategory(transaction.getCategory());
            newTrans.setMerchant(transaction.getMerchant());
            user.setNet_worth(user.getNet_worth()-transaction.getAmount());
            user.setCashFlow(user.getCashFlow()-transaction.getAmount());
            userData.save(user);
            return transData.save(newTrans);
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
    public Transaction updateTrans(Long userId, Long transId, Transaction transDetails) throws ResourceNotFoundException {
        return transData.findByIdAndUserId(transId, userId).map(transaction -> {
            double temp = transaction.getAmount();
            transaction.setAmount(transDetails.getAmount());
            transaction.setCategory(transDetails.getCategory());
            //transaction.setDate(transDetails.getDate());
            userData.findById(userId).map(user -> {
                user.setCashFlow(user.getCashFlow() + temp - transDetails.getAmount());
                user.setNet_worth(user.getNet_worth() + temp - transDetails.getAmount());
                return userData.save(user);
            });

            return transData.save(transaction);

        })
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not Found."));


    }

    public ResponseEntity<?> deleteTrans(Long userId, Long transId) throws ResourceNotFoundException{
        return transData.findByIdAndUserId(transId,userId).map(transaction ->{
            double temp = transaction.getAmount();
            transData.delete(transaction);
            userData.findById(userId).map(user -> {
                user.setCashFlow(user.getCashFlow() + temp);
                user.setNet_worth(user.getNet_worth() + temp);
                return userData.save(user);
            });
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Cannot find bill."));
    }

    public List<Transaction> showMyMonth(Long userId){
        LocalDate currDate = LocalDate.now();
        return transData.findByIdAndMonthAndYear(userId,currDate.getMonthValue(),currDate.getYear());

    }

    public List<Transaction> showMyYear(Long userId){
        LocalDate currDate = LocalDate.now();
        return transData.findByIdAndYear(userId, currDate.getYear());
    }






    }

