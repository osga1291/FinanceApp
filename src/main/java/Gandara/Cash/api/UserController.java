package Gandara.Cash.api;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserDao UserData;

    @GetMapping("/users")
    public List<User> findUsers(){
        return UserData.findAll();
    }
    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        UserData.save(user);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable(value ="id") Long userId) throws ResourceNotFoundException {
        User user = UserData.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable(value = "id") Long userId,
            @RequestBody User userDetails) throws ResourceNotFoundException{
        return UserData.findById(userId).map(user -> {
            user.setName(userDetails.getName());
            return UserData.save(user);
        })
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
            return UserData.findById(userId).map( user ->{
                UserData.delete(user);
                return ResponseEntity.ok().build();
            })
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));
    }








}
