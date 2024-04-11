package Gandara.Cash.Dao;

import Gandara.Cash.models.Bill;
import Gandara.Cash.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface transactionDao extends JpaRepository<Transaction,String> {
    List<Transaction> findByUserId(Long userId);
    Optional<Transaction> findByIdAndUserId(String id, Long userId);

    @Query(value = "select * from transaction where user_id = ?1 and month(date) = ?2 and year(date) = ?3 order by date;", nativeQuery = true)
    List<Transaction> findByIdAndMonthAndYear(Long Id, Integer month, Integer year);
    @Query(value = "select id, date(date), amount from transaction where user_id = ?1 and year(date) = ?3 order by date;", nativeQuery = true)
    List<Transaction> findByIdAndYear(Long Id, Integer year);
    @Query(value = "select id, date(date), amount from transaction where user_id = ?1 and month(date) > ?2 and year(date) = ?3 order by date;", nativeQuery = true)
    List<Transaction> find6Month(Long Id, Integer month, Integer year);
    @Query(value = "select id, amount, date(date), category, description, merchant, dtype from transaction;", nativeQuery = true)
    List<Transaction>findallList();





}  