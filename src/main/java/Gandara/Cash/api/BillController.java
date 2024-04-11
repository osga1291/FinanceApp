package Gandara.Cash.api;

import Gandara.Cash.Dao.BillDao;

import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.models.Bill;
import Gandara.Cash.Dao.transactionDao;
import Gandara.Cash.models.Transaction;
import Gandara.Cash.models.User;
import Gandara.Cash.service.BillService;
import Gandara.Cash.service.IBillService;
import Gandara.Cash.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class BillController {


   @Autowired
   BillDao billData;
   @Autowired
   BillService billService;
   @Autowired
   IUserService userService;


   @GetMapping("/bills")
    public List<Bill> findBills(){
        return billData.findAll();

    }

    @GetMapping("/me/add/bills")
    public String showBillForm(Model model){
       Bill newBill = new Bill();
       List<String> options = new ArrayList<>(Arrays.asList("monthly","yearly","biweekly"));
       model.addAttribute("bill", newBill);
       model.addAttribute("options",options);

       return "bill_form";

    }


    @PostMapping("/me/add/bills")
    public String submitBillForm(@ModelAttribute("bill") Bill bill, Model model) throws ResourceNotFoundException {

       billService.addBill(userService.currentUser(), bill);
       List<Bill> bills = billData.findByUserId(userService.currentUser());
       model.addAttribute("bills", bills);
       return "bill";
   }

    @GetMapping("/me/bills")
    public String getBillsByUser(Model model) throws ResourceNotFoundException{
       List<Bill> bills = billData.findByUserId(userService.currentUser());
       model.addAttribute("bills",bills);



        return "bill";

     }


    @PutMapping("/me/bills/{bill_id}")
    public Bill updateBill(@PathVariable(value = "bill_id") Long billId,
            @RequestBody Bill billDetails) throws ResourceNotFoundException {
        return billService.updateBill(userService.currentUser(), billId, billDetails);
    }


    @DeleteMapping("/me/bills/{bills_id}")
    public ResponseEntity<?> deleteBill(@PathVariable(value = "bill_id") Long billId)
            throws ResourceNotFoundException{
        return billService.deleteBill(userService.currentUser(), billId);
    }



    }

