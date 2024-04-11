package Gandara.Cash.models;

/*
import Gandara.Cash.Dao.ResourceNotFoundException;
import Gandara.Cash.Dao.UserDao;
import Gandara.Cash.Dao.cashFlowDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import java.util.*;

public class cashFlow {
    @Autowired
    cashFlowDao cashFlowDao;
    @Autowired
    UserDao user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int month;
    private int year;

    private LinkedHashMap<Integer, Double> entries;

    public cashFlow(){
    }

    public cashFlow(Date date, double amount){
        this.month = date.getMonth();
        this.year = date.getYear();
        this.entries = new LinkedHashMap<Integer, Double>();
        this.entries.put(date.getDate(),amount);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double setCash(Integer EntryKey,Double cash){

        return this.entries.put(EntryKey, cash);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LinkedHashMap<Integer, Double> getMonthAndYearEntries(Long userId, Date date) {
        return cashFlowDao.findByUserAndMonthAndYear(userId,date.getMonth(), date.getYear());

    }

    public void addEntry(Date date, double cash) {
        this.entries.put(date.getDate(),cash);
    }
    public double getCFbyDay(Date date, HashMap<Integer,Double> currMap){
        return currMap.get(date.getDate());
    }





}
*/