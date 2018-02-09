package OTS.tickets.OTSserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private boolean activate;

    private String code;

    public User() {
    }

    public User(String username, String password, String email, String phone, Integer level, Double point) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.point = point;
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

    public boolean isActivate() {
        return activate;
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

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
