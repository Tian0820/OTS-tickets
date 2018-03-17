package OTS.tickets.OTSserver.bean;

public class ShowPlanBean {

    private int showPlanId;

    private String venueCode;

    private String name;

    private String star;

    private String type;

    private String time;

    private String introduction;

    /**
     * 座位价格，由;分隔，从高到低
     */
    private String price;

    public ShowPlanBean() {
    }

    public ShowPlanBean(int showPlanId, String venueCode, String name, String star, String type, String time, String introduction, String price) {
        this.showPlanId = showPlanId;
        this.venueCode = venueCode;
        this.name = name;
        this.star = star;
        this.type = type;
        this.time = time;
        this.introduction = introduction;
        this.price = price;
    }

    public int getShowPlanId() {
        return showPlanId;
    }

    public void setShowPlanId(int showPlanId) {
        this.showPlanId = showPlanId;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getVenueCode() {
        return venueCode;
    }

    public void setVenueCode(String venueCode) {
        this.venueCode = venueCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
