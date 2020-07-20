package Gandara.Cash.Dao;

import Gandara.Cash.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDao extends JpaRepository<Bill,Long> {

    List<Bill> findByUserId(Long userId);
    Optional<Bill> findByIdAndUserId(Long id, Long userId);
}
