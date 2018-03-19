package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;

public interface OrderService {

    ResultMessageBean createOrder(OrderCreateBean order);


}
