package OTS.tickets.OTSserver.bean;

import OTS.tickets.OTSserver.model.Order;

import java.util.List;

public class OrderStatisticsBean {

    private List<Order> orders;

    private int orderNum;

    private double price;

    private String date;

    public OrderStatisticsBean() {
    }

    public OrderStatisticsBean(List<Order> orders, int orderNum, double price, String date) {
        this.orders = orders;
        this.orderNum = orderNum;
        this.price = price;
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
