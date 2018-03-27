package OTS.tickets.OTSserver.bean;

import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.ShowPlan;

import java.util.List;

public class VenueStatisticsBean {

    private String name;

    private String code;

    private double profit;

    private String address;

    private List<Order> orders;

    private List<ShowPlan> showPlans;

    public VenueStatisticsBean() {
    }

    public VenueStatisticsBean(String name, String code, double profit, String address, List<Order> orders, List<ShowPlan> showPlans) {
        this.name = name;
        this.code = code;
        this.profit = profit;
        this.address = address;
        this.orders = orders;
        this.showPlans = showPlans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ShowPlan> getShowPlans() {
        return showPlans;
    }

    public void setShowPlans(List<ShowPlan> showPlans) {
        this.showPlans = showPlans;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
