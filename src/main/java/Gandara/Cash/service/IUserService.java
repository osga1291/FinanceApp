package Gandara.Cash.service;

import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    User addCash(Long userId, double money) throws ResourceNotFoundException;
    User updateUser(Long userId, User userDetails) throws ResourceNotFoundException;
    ResponseEntity<?> deleteUser(Long userId) throws ResourceNotFoundException;
    Long currentUser() throws ResourceNotFoundException;
    User addUser(User user);
    User updateMoney(Long userId, double money)throws ResourceNotFoundException;
    String currentUserObj() throws ResourceNotFoundException;








}
