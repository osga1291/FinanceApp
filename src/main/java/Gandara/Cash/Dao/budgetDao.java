package Gandara.Cash.Dao;

import Gandara.Cash.models.Budget;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface budgetDao extends JpaRepository<Budget, Long> {

    List<Budget> findByUserId(Long userId);
    Budget findByUserIdAndMonthAndYear(Long userId, String month, String year);
    List<Budget> findByUserIdAndType(Long userId, String type);








}
