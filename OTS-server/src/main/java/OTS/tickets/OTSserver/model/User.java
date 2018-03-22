package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Integer level;

    private Double point;

    private Double accumulativePoint;

    /**
     * 是否被封禁，未被封禁为1，被封禁为0
     */
    private int activate;

    private String code;

    private Double balance;

    private Double consume;

    @ManyToMany(mappedBy = "users")
    @JsonManagedReference
    private List<Coupon> coupons;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Order> orders;

    public User() {
    }

    public User(String username, String password, String email, String phone, Integer level, Double point, Double accumulativePoint, int activate, Double balance, Double consume) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.point = point;
        this.accumulativePoint = accumulativePoint;
        this.activate = activate;
        this.balance = balance;
        this.consume = consume;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getLevel() {
        return level;
    }

    public Double getPoint() {
        return point;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getCode() {
        return code;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAccumulativePoint() {
        return accumulativePoint;
    }

    public void setAccumulativePoint(Double accumulativePoint) {
        this.accumulativePoint = accumulativePoint;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }
}
