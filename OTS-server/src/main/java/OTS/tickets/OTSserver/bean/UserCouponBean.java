package OTS.tickets.OTSserver.bean;

public class UserCouponBean {

    private int userId;

    private double discount;

    private int point;

    public UserCouponBean() {
    }

    public UserCouponBean(int userId, double discount, int point) {
        this.userId = userId;
        this.discount = discount;
        this.point = point;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
