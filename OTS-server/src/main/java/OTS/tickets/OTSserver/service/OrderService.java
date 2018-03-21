package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Order;

public interface OrderService {

    Order createOrder(OrderCreateBean order);

    ResultMessageBean payOrder(int orderId);

}
