package Gandara.Cash.Dao;

import Gandara.Cash.models.Bill;
import Gandara.Cash.models.Goal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface goalDao extends JpaRepository<Goal, Long>{


    List<Goal> findByUserId (Long UserId);
    Optional<Goal> findByIdAndUserId (Long Id, Long UserId);
    @Query(value = "select * from goal where user_id = ?1 and completed = false;", nativeQuery = true)
    List<Goal> findGoalInProgress(Long UserId);
    @Query(value = "select * from goal where user_id = ?1 and completed = true;", nativeQuery = true)
    List<Goal> findCompletedGoal(Long UserId);









}
