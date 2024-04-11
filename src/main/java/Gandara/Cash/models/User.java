package Gandara.Cash.models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String encryptedPassword;
    private double net_worth;
    private double CashFlow;
    private double CCPower;
    private double Invest;
    private String roles;
    private ArrayList<Double> CashFlowRecord;



    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;


    public User(){
    }

    public User(Long id, String name , String username, String encryptedPassword, int amount ,String roles) {
        Date buildDate = new Date();
        this.id = id;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.net_worth = 0.00;
        this.CashFlow = amount;
        this.CCPower = 0.00;
        this.Invest = 0.00;
        this.name = name;
        this.roles = roles;
        this.CashFlowRecord = new ArrayList<Double>();
        CashFlowRecord.add(0.00);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setNet_worth(double net_worth) {
        this.net_worth = net_worth;
    }

    public Long getId() {
        return id;
    }


    public double getCCPower() {
        return CCPower;
    }

    public double getInvest() {
        return Invest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getNet_worth() {
        return net_worth;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public double getCashFlow() {
        return CashFlow;
    }

    public void setCashFlow(double cashFlow) {
        CashFlow = cashFlow;
    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }



}
