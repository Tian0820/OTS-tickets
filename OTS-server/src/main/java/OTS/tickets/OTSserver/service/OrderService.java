package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.OrderStatisticsBean;
import OTS.tickets.OTSserver.bean.PayOrderBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderCreateBean order);

    ResultMessageBean payOrder(PayOrderBean payOrderBean);

    ResultMessageBean refundOrder(int orderId);

    Page<Order> getAllOrders(String state, int page, int size);

    List<OrderStatisticsBean> getOrdersWithinWeek();

    Order getOrderById(int orderId);

}
