package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.PayOrderBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping(
            value = "/order/create",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public Order createOrder(
            @RequestBody OrderCreateBean order) {
        return orderService.createOrder(order);
    }

    @ResponseBody
    @RequestMapping(
            value = "/order/pay",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean payOrder(
            @RequestBody PayOrderBean order) {
        return orderService.payOrder(order);
    }

    @ResponseBody
    @RequestMapping(
            value = "/order/refund/{orderId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean refundOrder(
            @PathVariable int orderId) {
        return orderService.refundOrder(orderId);
    }

    @ResponseBody
    @RequestMapping(
            value = "/order/get",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
