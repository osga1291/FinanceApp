package Gandara.Cash.models;
import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Table( name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String type;
    private double amount;
    private String deadline;
    private double amount_left;
    private boolean completed;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
    public Goal(){

    }

    public Goal(Long id, String type, double amount, String deadline) {

        this.id = id;
        this.type = type;
        this.amount = amount;
        this.amount_left = amount;
        this.completed = false;
        this.deadline = deadline;

    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setAmount_left(double amount_left) {
        this.amount_left = amount_left;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDeadline() {
        return deadline;
    }

    public double getAmount_left() {
        return amount_left;
    }


}
