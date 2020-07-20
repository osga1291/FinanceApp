package Gandara.Cash.models;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table( name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String type;
    private double amount;
    private Date deadline;
    private double amount_left;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
    public Goal(){

    }

    public Goal(Long id, String type, double amount, Date deadline) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deadline = deadline;
        this.amount_left = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDeadline(Date deadline) {
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

    public Date getDeadline() {
        return deadline;
    }

    public double getAmount_left() {
        return amount_left;
    }


}
