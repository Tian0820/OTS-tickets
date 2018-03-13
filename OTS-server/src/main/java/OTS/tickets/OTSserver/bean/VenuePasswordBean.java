package OTS.tickets.OTSserver.bean;

public class VenuePasswordBean {

    private String code;

    private String password;

    public VenuePasswordBean() {
    }

    public VenuePasswordBean(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
