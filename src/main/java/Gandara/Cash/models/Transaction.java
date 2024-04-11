package Gandara.Cash.models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "Transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;

    @ManyToOne
    @JoinColumn(name = "userId")
    public User user;

    public double amount;
    public String date;
    public String category;
    public String description;
    public String merchant;
    public String type;

    public Transaction(){

    }

    public Transaction(String id, User user, double amount, String date, String category, String merchant) {


        this.date = date;
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.category = category;
        this.merchant = merchant;
        this.type = "Regular";
    }

    protected LocalDate dateFormat(String date){

        return LocalDate.parse(date);
    }


    protected String dateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        return date.format(formatter);
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {

        return date;

    }

    public Date getDateFormatted() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        return formatter.parse(this.getDate());
    }

   public void setDate(String date) {

        this.date = date;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
