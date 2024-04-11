package Gandara.Cash.service;

import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Dao.goalDao;
import Gandara.Cash.models.Bill;
import Gandara.Cash.models.Goal;
import Gandara.Cash.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService implements IGoalService {
    @Autowired
    UserDao userData;
    @Autowired
    goalDao goalData;

    public Goal addGoal(Long userId, Goal goal) throws ResourceNotFoundException {
        return userData.findById(userId).map(user -> {
            Goal newGoal = new Goal();
            newGoal.setUser(user);
            newGoal.setAmount(goal.getAmount());
            newGoal.setDeadline(goal.getDeadline());
            newGoal.setType(goal.getType());
            return goalData.save(newGoal);
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public Goal updateGoal(Long userId, Long goalId, Goal goalDetails) throws ResourceNotFoundException {
        return goalData.findByIdAndUserId(goalId, userId).map(goal -> {
            goal.setAmount(goalDetails.getAmount());
            goal.setDeadline(goalDetails.getDeadline());
            goal.setType(goalDetails.getType());
            goal.setCompleted(goalDetails.isCompleted());
            goal.setAmount_left(goalDetails.getAmount_left());
            return goalData.save(goal);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Goal not Found"));


    }

    public ResponseEntity<?> deleteGoal(Long userId, Long goalId)
            throws ResourceNotFoundException {
        return goalData.findByIdAndUserId(goalId, userId).map(goal -> {
            goalData.delete(goal);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Cannot find bill."));

    }


}