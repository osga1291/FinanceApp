package Gandara.Cash.models;

import java.time.LocalDate;
import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

@Entity
@Table(name = "Bills")

public class Bill extends Transaction {

    private String occurrence;
    private String dueDate;

    

    public Bill(){

    }

    public Bill(String id, User user,  double amount, String date, String due_date, String category, String merchant, String occurrence) {
        super(id,user,amount,date,category,merchant);
        this.occurrence = occurrence;
        this.type = "Bill";
        this.dueDate = due_date;
        //this.setNextDueDate();
    }

    public void setDueDate(String date){

        this.dueDate = date;
    }


    public String getDueDate(){


        return this.dueDate;
    }


    public void setNextDueDate(){

        LocalDate myDate = this.dateFormat(dueDate);

        if (occurrence.equals("monthly")) {
                myDate = myDate.plusMonths(1);

        }
        else if( occurrence.equals("yearly")){
                myDate = myDate.plusYears(1);}
        else{
                myDate = myDate.plusWeeks(2);
        }
        dueDate = this.dateToString(myDate);
    }




    public void setOccurrence(String occurrence) {

        this.occurrence = occurrence;
    }

    public String getOccurrence() {

        return occurrence;
    }

    public String getCurrentDate() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now) + " 00:00:00";
        //return now.toString();


    }
}
