package Gandara.Cash.service;

import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.models.Bill;
import Gandara.Cash.models.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public interface IBillService  {
    Bill addBill(Long userId, Bill bill) throws ResourceNotFoundException;
    Bill updateBill(Long userId, Long billId, Bill bill) throws ResourceNotFoundException;
    ResponseEntity<?> deleteBill(Long userId, Long billId) throws ResourceNotFoundException;
    void checkAllDueDate() throws ResourceNotFoundException;
    User updateMoney(Long userId, double amount) throws ResourceNotFoundException;
    //Boolean checkifBillisDue(Bill bill) throws ResourceNotFoundException;


}
