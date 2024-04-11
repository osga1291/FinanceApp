package Gandara.Cash.api;



import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.goalDao;
import Gandara.Cash.models.Goal;
import Gandara.Cash.models.Transaction;
import Gandara.Cash.service.IGoalService;
import Gandara.Cash.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class GoalController {
    @Autowired
    goalDao goalDao;
    @Autowired
    IGoalService goalService;
    @Autowired
    IUserService userService;

    @GetMapping("/me/goals/in-progress")
    public String inProgressGoals(Model model) throws ResourceNotFoundException {
        List<Goal> goals = goalDao.findGoalInProgress(userService.currentUser());
        model.addAttribute("goals", goals);

        return "goal";

    }

    @GetMapping("/me/goals/completed")
    public String completedGoals(Model model)throws ResourceNotFoundException{
        List<Goal> goals = goalDao.findCompletedGoal(userService.currentUser());
        model.addAttribute("goals", goals);

        return "goal";
    }
    @GetMapping("/me/goals/all")
    public String allGoals(Model model) throws ResourceNotFoundException{
        List<Goal> goals = goalDao.findByUserId(userService.currentUser());

        model.addAttribute("goals", goals);

        return "goal";

    }

    @GetMapping("/me/add/goal")
    public String goalForm(Model model){
        Goal goal = new Goal();
        model.addAttribute("goals", goal);

        return "goal_form";
    }

    @PostMapping("/me/add/goal")
    public String postGoalForm(@ModelAttribute("goal") Goal goal, Model model) throws ResourceNotFoundException{
        goalService.addGoal(userService.currentUser(),goal);
        List<Goal> goals = goalDao.findByUserId(userService.currentUser());
        model.addAttribute("goals", goals);

        return "goal";
    }


    @GetMapping("/me/goals/{goalId}")
    public Goal getGoal(@PathVariable(name = "goalId") Long goalId) throws ResourceNotFoundException{
        return goalDao.findByIdAndUserId(goalId, userService.currentUser())
                .orElseThrow(()-> new ResourceNotFoundException("Goal not found."));
        }

    @PutMapping("/me/goals/{goalId}")
    public Goal updateGoal(@PathVariable(name = "goalId") Long goalId, @RequestBody Goal goal) throws ResourceNotFoundException{
        return goalService.updateGoal(userService.currentUser(),goalId,goal);
    }
    @DeleteMapping ("/me/goals/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable(name = "goalId") Long goalId) throws ResourceNotFoundException{
        return goalService.deleteGoal(userService.currentUser(),goalId);
    }













}
