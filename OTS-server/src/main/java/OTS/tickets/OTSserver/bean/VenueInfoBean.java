package OTS.tickets.OTSserver.bean;

public class VenueInfoBean {

    private int venue_id;

    private String code;

    private String venue_name;

    private String city;

    private String address;

    private String seat_type;

    private String password;

    public VenueInfoBean() {
    }

    public VenueInfoBean(int venue_id, String code, String venue_name, String city, String address, String seat_type, String password) {
        this.venue_id = venue_id;
        this.code = code;
        this.venue_name = venue_name;
        this.city = city;
        this.address = address;
        this.seat_type = seat_type;
        this.password = password;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
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

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
