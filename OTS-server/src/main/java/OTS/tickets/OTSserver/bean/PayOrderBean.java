package OTS.tickets.OTSserver.bean;

import OTS.tickets.OTSserver.model.Coupon;

public class PayOrderBean {

    private int orderId;

    private double price;

    private int couponId;

    public PayOrderBean() {
    }

    public PayOrderBean(int orderId, double price, int couponId) {
        this.orderId = orderId;
        this.price = price;
        this.couponId = couponId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}
