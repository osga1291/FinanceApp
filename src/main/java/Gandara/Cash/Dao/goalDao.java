package Gandara.Cash.Dao;

import Gandara.Cash.models.Goal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface goalDao extends JpaRepository<Goal, Long>{


    List<Goal> findByUserId (Long UserId);
    <Optional> Goal  findByIdAndUserId (Long Id, Long UserId);


}
