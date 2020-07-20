package Gandara.Cash.models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String type;
    public String merchant;
    private double amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId")

    private User user;

    public Payment(Long id, double amount, Date date, String type, String merchant) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.merchant = merchant;

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public String getMerchant() {
        return merchant;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }


}
