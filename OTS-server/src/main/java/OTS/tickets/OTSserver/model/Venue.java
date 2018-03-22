package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    private String venueName;

    private String city;

    private String address;

    private String seatType;

    private String password;

    private Double balance;

    @OneToMany(mappedBy = "venue")
    @JsonBackReference
    private List<ShowPlan> showPlans;

    @OneToMany(mappedBy = "venue")
    @JsonBackReference
    private List<Approval> approvals;

    public Venue() {
    }

    public Venue(String code, String venueName, String city, String address, String seatType, String password, Double balance) {
        this.code = code;
        this.venueName = venueName;
        this.city = city;
        this.address = address;
        this.seatType = seatType;
        this.password = password;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<ShowPlan> getShowPlans() {
        return showPlans;
    }

    public void setShowPlans(List<ShowPlan> showPlans) {
        this.showPlans = showPlans;
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }
}
