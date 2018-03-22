package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "approval")
public class Approval {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 审批状态，分为：
     * 成功，失败，审批中
     */
    private String state;

    /**
     * 审批类型，分为：
     * 注册
     * 修改
     */
    private String type;

    private String time;

    @ManyToOne
    @JsonManagedReference
    private Venue venue;

    public Approval() {
    }

    public Approval(String state, String type, String time, Venue venue) {
        this.state = state;
        this.type = type;
        this.time = time;
        this.venue = venue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}

