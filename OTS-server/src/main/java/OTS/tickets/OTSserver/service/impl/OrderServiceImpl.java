package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
import OTS.tickets.OTSserver.bean.OrderStatisticsBean;
import OTS.tickets.OTSserver.bean.PayOrderBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.*;
import OTS.tickets.OTSserver.repository.*;
import OTS.tickets.OTSserver.service.OrderService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    CouponRepository couponRepository;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public Order createOrder(OrderCreateBean order) {
        ResultMessageBean result = new ResultMessageBean(true);

        User user = userRepository.findUserById(order.getUserId());
        ShowPlan showPlan = showPlanRepository.findShowPlanByIdOrderBySeatsAsc(order.getShowId());

        Date date = new Date();

        String[] seats = order.getSeats().split(";");
        List<Seat> seatList = new ArrayList<>();
        if (order.getType().equals("选座")) {
            for (String seatId :
                    seats) {
                seatList.add(seatRepository.findSeatById(Integer.valueOf(seatId)));
            }

            Order newOrder = new Order(showPlan, user, order.getType(),
                    order.getState(), df.format(date), null, order.getPrice(), seatList.size(), seatList);
            orderRepository.save(newOrder);

            for (int i = 0; i < seatList.size(); i++) {
                Seat seat = seatRepository.findSeatById(seatList.get(i).getId());
                seat.setAvailable(0);
                seat.setOrder(newOrder);
                seatRepository.save(seat);
            }
            return newOrder;
        } else {
            Order newOrder = new Order(showPlan, user, order.getType(),
                    order.getState(), df.format(date), null, order.getPrice(), seats.length, null);
            orderRepository.save(newOrder);
            return newOrder;
        }

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
                    Coupon coupon = couponRepository.findCouponById(payOrderBean.getCouponId());
                    if (balance < 0) {
                        result.message = "余额不足！";
                        return result;
                    } else {
                        if (coupon != null) {
                            System.out.println("coupons remove!!!");
                            List<Coupon> coupons = user.getCoupons();
                            coupons.remove(coupon);
                            List<User> users = coupon.getUsers();
                            users.remove(order.getUser());

                            System.out.println(coupons.size());

                            user.setCoupons(coupons);
                            coupon.setUsers(users);
                        }
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

    @Override
    public ResultMessageBean refundOrder(int orderId) {
        Order order = orderRepository.findOrderById(orderId);
        User user = order.getUser();
        ResultMessageBean result = new ResultMessageBean(false);
        Date now = new Date();
        Date showDate = null;
        try {
            showDate = df.parse(order.getShowPlan().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long interval = (showDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);
        if (interval < 7) {
            result.message = "演出开始前一周无法退款！";
            return result;
        } else if (interval < 30) {
            // 演出开始前一月退回一半票价
            double balance = user.getBalance() + order.getPrice() / 2;
            user.setBalance(balance);
            userRepository.save(user);
            order.setState("已退款");
            order.setSeatNum(0);
            orderRepository.save(order);

            List<Seat> seats = order.getSeats();
            if (seats != null && seats.size() != 0) {
                for (Seat seat :
                        seats) {
                    seat.setOrder(null);
                    seat.setAvailable(1);
                    seatRepository.save(seat);
                }
            }

            result.result = true;
            return result;
        } else {
            // 距离演出开始大于一个月，退回全部
            double balance = user.getBalance() + order.getPrice();
            user.setBalance(balance);
            userRepository.save(user);
            order.setState("已退款");
            order.setSeatNum(0);
            orderRepository.save(order);

            List<Seat> seats = order.getSeats();
            if (seats != null && seats.size() != 0) {
                for (Seat seat :
                        seats) {
                    seat.setOrder(null);
                    seat.setAvailable(1);
                    seatRepository.save(seat);
                }
            }

            result.result = true;
            return result;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderStatisticsBean> getOrdersWithinWeek() {
        Calendar cal = Calendar.getInstance();
        List<OrderStatisticsBean> orderStatistics = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            cal.setTime(new Date());
            System.out.print(simpleDate.format(cal.getTime()));

            cal.add(Calendar.DATE, -i);
            String date = simpleDate.format(cal.getTime());

            System.out.println(date);

            List<Order> orders = orderRepository.findOrderByCreateTimeStartingWith(date);
            double price = 0.0;
            for (Order order :
                    orders) {
                price += order.getPrice();
            }
            orderStatistics.add(new OrderStatisticsBean(orders, orders.size(), price, date));
        }

        return orderStatistics;
    }
}
