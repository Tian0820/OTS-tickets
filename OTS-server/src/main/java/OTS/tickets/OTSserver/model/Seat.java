package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue
    private Integer id;

    private String area;

    private Integer number;

    /**
     * 是否已卖出，已卖出为0，未卖出为1
     */
    private Integer available;

    private Double price;

    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JsonBackReference
    private ShowPlan showPlan;

    public Seat() {
    }

    public Seat(String area, Integer number, Integer available, Double price, Order order, ShowPlan showPlan) {
        this.area = area;
        this.number = number;
        this.available = available;
        this.price = price;
        this.order = order;
        this.showPlan = showPlan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShowPlan getShowPlan() {
        return showPlan;
    }

    public void setShowPlan(ShowPlan showPlan) {
        this.showPlan = showPlan;
    }
}
