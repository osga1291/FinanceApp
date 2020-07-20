package Gandara.Cash.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private double amount;
    private Date due_date;
    private String type;
    private String merchant;

    public Bill(){

    }

    public Bill(Long id, double amount, String due_date, String type, String merchant) {
        try{
            this.due_date = new SimpleDateFormat("yyyy-MM-dd").parse(due_date);
        }catch (ParseException e){
            this.due_date = null; }

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getMerchant() {
        return merchant;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDue_date() {
        return due_date;
    }

    public String getType() {
        return type;
    }

}
