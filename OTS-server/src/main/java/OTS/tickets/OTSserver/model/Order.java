package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ticket_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    private ShowPlan showPlan;

    @ManyToOne
    @JsonManagedReference
    private User user;

    /**
     * 订单类型，分为"选座"和"分配"
     */
    private String type;

    /**
     * 订单状态，分为"未付款"，"已过期"，"已付款"，"已完成"，"已退订"
     */
    private String state;

    private String createTime;

    private String finishTime;

    private Double price;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<Seat> seats;

    public Order() {
    }

    public Order(ShowPlan showPlan, User user, String type, String state, String createTime, String finishTime, Double price, List<Seat> seats) {
        this.showPlan = showPlan;
        this.user = user;
        this.type = type;
        this.state = state;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.price = price;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShowPlan getShowPlan() {
        return showPlan;
    }

    public void setShowPlan(ShowPlan showPlan) {
        this.showPlan = showPlan;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
