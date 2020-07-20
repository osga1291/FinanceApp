package Gandara.Cash.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")

public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double net_worth;
    private double CashFlow;
    private double CCPower;
    private double Invest;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    public User(){
    }

    public User(Long id, String name) {
        this.id = id;
        this.net_worth = 0.00;
        this.CashFlow = 0.00;
        this.CCPower = 0.00;
        this.Invest = 0.00;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public double getCashFlow() {
        return CashFlow;
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


}
