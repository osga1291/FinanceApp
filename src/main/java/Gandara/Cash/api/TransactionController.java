package Gandara.Cash.api;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Dao.transactionDao;
import Gandara.Cash.models.Bill;
import Gandara.Cash.models.Transaction;
import Gandara.Cash.models.User;
import Gandara.Cash.service.ITransactionService;
import Gandara.Cash.service.IUserService;
import Gandara.Cash.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    UserDao userData;
    @Autowired
    transactionDao transData;
    @Autowired
    ITransactionService transService;
    @Autowired
    IUserService userService;

    @GetMapping("/me/transaction/all")
    public String findTrans(Model model){
        List<Transaction> list = transData.findallList();
        model.addAttribute("trans", list);

        return "my_trans.html";

    }


/*
    @GetMapping("me/dashboard")
    public String getGraphData(Model model) throws ResourceNotFoundException, ParseException {
        Map<Date, Double> graphData = new HashMap<>();
        List<Transaction> transDate = transService.showMyMonth(userService.currentUser());
        for (Transaction trans :transDate){
            graphData.put(trans.getDateFormatted() ,trans.getAmount());
        }
        model.addAttribute("graphData",graphData);

        return "graphData";
    }
*/

    @GetMapping("/me/all_transaction")
    public String getTransByUser(Model model) throws ResourceNotFoundException{
        List<Transaction> trans = transData.findByUserId(userService.currentUser());
        model.addAttribute("trans", trans);

        return "my_trans";
    }
    @GetMapping("/me/add_transaction")
    public String transactionForm(Model model){
        Transaction trans = new Transaction();
        model.addAttribute("trans", trans);

        return "transForm";
    }




    @PostMapping("/me/add_transaction")
    public String postTransForm(@ModelAttribute("trans") Transaction transaction, Model model) throws ResourceNotFoundException{
        transService.addTrans(userService.currentUser(),transaction);
        List<Transaction> trans = transData.findByUserId(userService.currentUser());
        model.addAttribute("trans",trans);



        return "my_trans";
    }

    @PutMapping("/me/transaction/{trans_id}")
    public Transaction updateTransaction(@PathVariable (value = "trans_id") Long transId,
                                         @RequestBody Transaction transDetails)
            throws ResourceNotFoundException{
        return transService.updateTrans(userService.currentUser(),transId,transDetails);

    }


    @DeleteMapping("/me/transaction/{trans_id}")
    public ResponseEntity<?> deleteTrans(@PathVariable(value = "trans_id") Long transId)
            throws ResourceNotFoundException{
        return transService.deleteTrans(userService.currentUser(), transId);
    }


}


