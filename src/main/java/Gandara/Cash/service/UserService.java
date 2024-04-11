package Gandara.Cash.service;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService{
    @Autowired
    UserDao userData;

    Long id;

    public Long currentUser() throws ResourceNotFoundException {

        if (id == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currPrinciple = authentication.getName();


            id = userData.findByUsername(currPrinciple).map(user -> user.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found."));
        }
        return id;


    }

    public String currentUserObj() throws ResourceNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currPrinciple = authentication.getName();
        //System.out.println(currPrinciple);
        Optional <User> currUser = userData.findByUsername(currPrinciple);


        return currPrinciple;

    }





    public User addUser(User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setNet_worth(user.getNet_worth());
        newUser.setCashFlow(user.getCashFlow());
        newUser.setUsername(user.getUsername());
        newUser.setEncryptedPassword(user.getEncryptedPassword());
        newUser.setRoles("USER");
        return userData.save(newUser);
    }

    public User addCash(Long userId, double money) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {
            user.setNet_worth(user.getNet_worth() + money);
            user.setCashFlow(user.getCashFlow() + money);
            return userData.save(user);
        })

                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

    }
    public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException{
        return userData.findById(userId).map(user -> {
            user.setName(userDetails.getName());
            return userData.save(user);
        })
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }


    public User updateMoney(Long userId,double amount) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {
            user.setCashFlow(user.getCashFlow() + amount);
            user.setNet_worth(user.getNet_worth() + amount);
            return userData.save(user);
        })
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));
    }


    public ResponseEntity<?> deleteUser(Long userId) throws ResourceNotFoundException{
        return userData.findById(userId).map( user ->{
            userData.delete(user);
            return ResponseEntity.ok().build();
        })
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));
    }











             








}

