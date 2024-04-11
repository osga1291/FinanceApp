package Gandara.Cash.models;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "budget")
public class Budget {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "userId")
        private User user;
        private double spent;
        private Date month;
        private Date year;
        private double amount;
        private String type;

        public Budget(){

        }

        public Budget(Long id, double amount, String type, Date month, Date year, User user){

            this.user = user;
            this.id = id;
            this.amount = amount;
            this.type = type;
            this.month = month;
            this.year = year;
            this.spent = 0.00;


        }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = this.getSpent() + spent;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
