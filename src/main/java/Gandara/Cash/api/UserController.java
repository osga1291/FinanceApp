package Gandara.Cash.api;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Security.MyUserDetails;
import Gandara.Cash.models.Transaction;
import Gandara.Cash.models.User;

import Gandara.Cash.service.IUserService;

import Gandara.Cash.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/api")

@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserDao UserData;
    @Autowired
    IUserService userService;

    @Autowired
    TransactionService transService;


    @GetMapping("/me/dashboard")
    public String getCurrentUser(Model model) throws ResourceNotFoundException {
        List<Object> list = new ArrayList<Object>();

        return UserData.findById(userService.currentUser()).map(user -> {
            list.add(user.getName());
            list.add(user.getNet_worth());
            list.add(user.getCashFlow());
            list.add(user.getCCPower());
            list.add(user.getInvest());
            List<Transaction> spent = new ArrayList<>();

            spent = transService.showMyMonth(user.getId());



            System.out.println(spent);

            model.addAttribute("user",list);
            model.addAttribute("spentData", spent);

            return "dashboard";

        })
                .orElseThrow(()->new ResourceNotFoundException("User not found."));

    }




    @GetMapping ("/add_me")
    public String addUserForm(Model model){
        User newUser = new User();
        model.addAttribute("user",newUser);

        return "new_user";


        }

    @PostMapping ("/add_me")
    public String submitUserForm(@ModelAttribute("user") User user){

        userService.addUser(user);
        return "sign-in";


    }


    @PutMapping("/me/")
    public User updateUser(@RequestBody User userDetails) throws ResourceNotFoundException{
        return userService.updateUser(userService.currentUser(), userDetails);
    }

    @DeleteMapping("/me/")
    public ResponseEntity<?> deleteUser() throws ResourceNotFoundException{
            return userService.deleteUser(userService.currentUser());
    }

    @PutMapping("/me/addMoney")
    public User addCash(@RequestParam("money") double money) throws ResourceNotFoundException {
        return userService.addCash(userService.currentUser(), money); }








}
