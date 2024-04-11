package Gandara.Cash.service;


import Gandara.Cash.Dao.BillDao;
import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Security.UserFunctions;
import Gandara.Cash.models.Bill;
import Gandara.Cash.models.Transaction;
import Gandara.Cash.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.sql.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

@EnableAsync(proxyTargetClass = true)
@Service
public class BillService implements IBillService {
    @Autowired
    UserDao userData;
    @Autowired
    BillDao billData;
    @Autowired
    UserService userService;




    public Bill addBill(Long userId, Bill bill) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {

            Bill newBill = new Bill();
            newBill.setUser(user);
            newBill.setAmount(bill.getAmount());
            newBill.setDate(bill.getDate());
            newBill.setDueDate(bill.getDueDate());
            newBill.setCategory(bill.getCategory());
            newBill.setMerchant(bill.getMerchant());
            newBill.setOccurrence(bill.getOccurrence());
            return billData.save(newBill);


        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public Bill updateBill(Long userId, Long billId, Bill billDetails) throws ResourceNotFoundException {
        return billData.findByIdAndUserId(billId, userId).map(bill -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            bill.setAmount(billDetails.getAmount());
            bill.setCategory(billDetails.getCategory());
            bill.setMerchant(billDetails.getMerchant());
            bill.setDate(billDetails.getDate());
            bill.setDueDate(billDetails.getDueDate());
            bill.setOccurrence(billDetails.getOccurrence());
            return billData.save(bill);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));
    }

    public ResponseEntity<?> deleteBill(Long userId, Long billId)
            throws ResourceNotFoundException {
        return billData.findByIdAndUserId(billId, userId).map(bill -> {
            billData.delete(bill);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Cannot find bill."));

    }

    public User updateMoney(Long userId,double amount) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {
            user.setCashFlow(user.getCashFlow() + amount);
            user.setNet_worth(user.getNet_worth() + amount);
            return userData.save(user);
        })
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));
    }
    @Bean(name = "threadPoolTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("AsynchThread::");
        executor.initialize();
        return executor;
    }



    @Scheduled(fixedRate =100000, initialDelay = 9000)
    //cron = "0 15 10 15 * ?"
    public void checkAllDueDate() throws ResourceNotFoundException {

        Long userId = userService.currentUser();


        //System.out.println(userId);
        if (userId != null) {
            List<Bill> myBill = billData.findByUserId(userId);
            for (Bill bill : myBill) {
                //System.out.println(bill.getDate());

                if (checkifBillisDue(bill)) {
                    updateMoney(userId, -bill.getAmount());

                }

            }

        }
        System.out.println("Hello");
    }


    private Boolean checkifBillisDue(Bill bill)throws ResourceNotFoundException{
        System.out.println("Due date: "+ bill.getDueDate());
        System.out.println("Current Date: " + bill.getCurrentDate());
        if (bill.getDueDate().equals(bill.getCurrentDate())){

            return true;

        }
        return false;
    }



}


