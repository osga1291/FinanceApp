package Gandara.Cash.service;


import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.models.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IGoalService {

    Goal addGoal(Long userId, Goal goal) throws ResourceNotFoundException;
    Goal updateGoal(Long userId, Long goalId, Goal goal) throws ResourceNotFoundException;
    ResponseEntity<?> deleteGoal(Long userId, Long goalId) throws ResourceNotFoundException;
    //List<Double> predict_savings(Long userId, Integer months) throws ResourceNotFoundException;



}
