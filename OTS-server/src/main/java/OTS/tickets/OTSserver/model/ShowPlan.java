package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "show_plan")
public class ShowPlan {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String star;

    private String time;

    private String type;

    private String introduction;

    @OneToMany(mappedBy = "showPlan")
    @JsonManagedReference
    private List<Seat> seats;

    @ManyToOne
    @JsonManagedReference
    private Venue venue;

    @OneToMany(mappedBy = "showPlan")
    @JsonBackReference
    private List<Order> orders;


    public ShowPlan() {
    }

    public ShowPlan(String name, String star, String time, String type, String introduction, Venue venue) {
        this.name = name;
        this.star = star;
        this.time = time;
        this.type = type;
        this.introduction = introduction;
        this.venue = venue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
