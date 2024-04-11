package Gandara.Cash.service;
import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.models.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    Transaction addTrans(Long Id, Transaction transaction) throws ResourceNotFoundException;
    Transaction updateTrans(Long userId,Long transId, Transaction transaction) throws ResourceNotFoundException;
    ResponseEntity<?> deleteTrans(Long userId, Long transId) throws ResourceNotFoundException;
    List<Transaction> showMyMonth(Long userId);
    List<Transaction> showMyYear(Long userId);


}
