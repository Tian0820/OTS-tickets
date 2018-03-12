package OTS.tickets.OTSserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue
    private Integer id;

    private String code;

    private String venueName;

    private String city;

    private String address;

    private String seatType;

    private String password;

    public Venue() {
    }

    public Venue(String code, String venueName, String city, String address, String seatType, String password) {
        this.code = code;
        this.venueName = venueName;
        this.city = city;
        this.address = address;
        this.seatType = seatType;
        this.password = password;
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
}
