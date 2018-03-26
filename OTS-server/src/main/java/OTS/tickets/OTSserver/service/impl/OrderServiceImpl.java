package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.PayOrderBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.Seat;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.repository.OrderRepository;
import OTS.tickets.OTSserver.repository.SeatRepository;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.repository.UserRepository;
import OTS.tickets.OTSserver.service.OrderService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowPlanRepository showPlanRepository;

    @Autowired
    SeatRepository seatRepository;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Order createOrder(OrderCreateBean order) {
        ResultMessageBean result = new ResultMessageBean(true);

        User user = userRepository.findUserById(order.getUserId());
        ShowPlan showPlan = showPlanRepository.findShowPlanByIdOrderBySeatsAsc(order.getShowId());

        Date date = new Date();

        String[] seats = order.getSeats().split(";");
        List<Seat> seatList = new ArrayList<>();
        for (String seatId :
                seats) {
            seatList.add(seatRepository.findSeatById(Integer.valueOf(seatId)));
        }

        Order newOrder = new Order(showPlan, user, order.getType(),
                order.getState(), df.format(date), null, order.getPrice(), seatList);
        orderRepository.save(newOrder);

        for (int i = 0; i < seatList.size(); i++) {
            Seat seat = seatRepository.findSeatById(seatList.get(i).getId());
            seat.setAvailable(0);
            seat.setOrder(newOrder);
            seatRepository.save(seat);
        }

        return newOrder;
    }

    @Override
    public ResultMessageBean payOrder(PayOrderBean payOrderBean) {

        ResultMessageBean result = new ResultMessageBean(false);

        Order order = orderRepository.findOrderById(payOrderBean.getOrderId());
        order.setPrice(payOrderBean.getPrice());

        //检查是否在15分钟内付款
        Date date = new Date();
        try {
            Date createDate = df.parse(order.getCreateTime());
            long interval = (date.getTime() - createDate.getTime()) / (1000 * 60);
            if (interval > 15) {
                //订单过期，解除已锁定的座位
                System.out.println("===================过期！！！");
                List<Seat> seats = order.getSeats();
                for (int i = 0; i < seats.size(); i++) {
                    Seat seat = seatRepository.findSeatById(seats.get(i).getId());
                    seat.setAvailable(1);
                    seat.setOrder(null);
                    seatRepository.save(seat);
                }
                result.message = "订单已过期，请重新下单！";
            } else {
                //订单正常
                User user = order.getUser();
                if (user != null) {
                    Double balance = user.getBalance() - order.getPrice();
                    if (balance < 0) {
                        result.message = "余额不足！";
                        return result;
                    } else {
                        user.setBalance(balance);
                    }
                }
                order.setState("已付款");
                orderRepository.save(order);
                result.result = true;
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
