package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.OrderStatisticsBean;
import OTS.tickets.OTSserver.bean.PayOrderBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderCreateBean order);

    ResultMessageBean payOrder(PayOrderBean payOrderBean);

    ResultMessageBean refundOrder(int orderId);

    List<Order> getAllOrders();

    List<OrderStatisticsBean> getOrdersWithinWeek();

    Order getOrderById(int orderId);

}
