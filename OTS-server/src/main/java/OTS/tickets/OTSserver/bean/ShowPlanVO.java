package OTS.tickets.OTSserver.bean;

import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.model.Venue;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

public class ShowPlanVO {
    public Integer id;

    public String name;

    public String star;

    public String time;

    public String type;

    public String introduction;

    public Set<Double> prices;

    public Venue venue;

    public List<Order> orders;

    public ShowPlanVO(ShowPlan showPlan, Set<Double> prices) {
        this.id = showPlan.getId();
        this.name = showPlan.getName();
        this.star = showPlan.getStar();
        this.time = showPlan.getTime();
        this.type = showPlan.getType();
        this.introduction = showPlan.getIntroduction();
        this.prices = prices;
        this.venue = showPlan.getVenue();
        this.orders = showPlan.getOrders();
    }
}
