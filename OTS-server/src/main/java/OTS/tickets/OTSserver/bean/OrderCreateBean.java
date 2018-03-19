package OTS.tickets.OTSserver.bean;

import OTS.tickets.OTSserver.model.Seat;

import java.util.List;

public class OrderCreateBean {

    private Integer orderId;

    private Integer showId;

    private Integer userId;

    /**
     * 订单类型，分为"选座"和"分配"
     */
    private String type;

    /**
     * 订单状态，分为"未付款"，"已过期"，"已付款"，"已完成"，"已退订"
     */
    private String state;

    private String createTime;

    private Double price;

    private List<Seat> seats;

    public OrderCreateBean() {
    }

    public OrderCreateBean(Integer orderId, Integer showId, Integer userId, String type, String state, String createTime, Double price, List<Seat> seats) {
        this.orderId = orderId;
        this.showId = showId;
        this.userId = userId;
        this.type = type;
        this.state = state;
        this.createTime = createTime;
        this.price = price;
        this.seats = seats;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
