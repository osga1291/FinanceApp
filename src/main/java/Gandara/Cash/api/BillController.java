package Gandara.Cash.api;

import Gandara.Cash.Dao.BillDao;

import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.models.Bill;
import Gandara.Cash.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    UserDao userData;
    @Autowired
    BillDao billData;

    @GetMapping("/bills")
    public List<Bill> findBills(){
        return billData.findAll();

    }
    @PostMapping("/users/{userId}/bills")
    public Bill addBills(@PathVariable(value = "userId") Long userId, @RequestBody Bill bill) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {
            bill.setUser(user);
            return billData.save(bill);
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

        @GetMapping("/users/{userId}/bills")
    public List<Bill> getBillsByUser(@PathVariable(value = "userId") Long userId){
        return billData.findByUserId(userId);
     }
    @PutMapping("/users/{id}/bills/{bill_id}")
    public Bill updateBill(
            @PathVariable(value = "id") Long userId,
            @PathVariable(value = "bill_id") Long billId,
            @RequestBody Bill billDetails) throws ResourceNotFoundException {
        return billData.findByIdAndUserId(billId, userId).map(bill -> {
            bill.setAmount(billDetails.getAmount());
            bill.setDue_date(billDetails.getDue_date());
            bill.setMerchant(billDetails.getMerchant());
            return billData.save(bill);
        })
                .orElseThrow(()-> new ResourceNotFoundException("Bill not found"));
    }
    @DeleteMapping("users/{id}/bills/{bills_id}")
    public ResponseEntity<?> deleteBill(@PathVariable(value = "id") Long userId,
                           @PathVariable(value = "bill_id") Long billId)
            throws ResourceNotFoundException{
        return billData.findByIdAndUserId(billId,userId).map( bill ->{
                billData.delete(bill);
                return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Cannot find bill."));
    }


    }

