package OTS.tickets.OTSserver.bean;

public class VenueInfoBean {

    private int venueId;

    private String code;

    private String venueName;

    private String city;

    private String address;

    private String seatType;

    private String password;

    private double balance;

    public VenueInfoBean() {
    }

    public VenueInfoBean(int venueId, String code, String venueName, String city, String address, String seatType, String password, double balance) {
        this.venueId = venueId;
        this.code = code;
        this.venueName = venueName;
        this.city = city;
        this.address = address;
        this.seatType = seatType;
        this.password = password;
        this.balance = balance;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
