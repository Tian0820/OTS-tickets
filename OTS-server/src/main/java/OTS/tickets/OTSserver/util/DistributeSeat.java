package OTS.tickets.OTSserver.util;

import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.Seat;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.repository.OrderRepository;
import OTS.tickets.OTSserver.repository.SeatRepository;
import OTS.tickets.OTSserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DistributeSeat {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "0 0 6 * * *")
    public void distribute() {
        try {
            List<Order> orders = orderRepository.findOrderByType("分配");
            if ((orders != null) && (!orders.isEmpty())) {
                for (Order order : orders) {
                    List<Seat> seats = order.getSeats();
                    if (order.getState().equals("已付款") && seats == null || seats.isEmpty()) {

                        Date now = new Date();
                        Date showTime = df.parse(order.getShowPlan().getTime());
                        long interval = (showTime.getTime() - now.getTime()) / (1000 * 60 * 60 * 24 * 7);

                        if (interval <= 2) {
                            // 演出开始前两周自动配票
                            List<Seat> showPlanSeats = order.getShowPlan().getSeats();
                            List<Seat> orderSeatsToSet = new ArrayList<>();
                            for (int i = 0; i < showPlanSeats.size(); i++) {

                                System.out.println(i + "     " + showPlanSeats.get(i).getAvailable());

                                if (showPlanSeats.get(i).getAvailable().equals(1)) {
                                    System.out.println("available");

                                    showPlanSeats.get(i).setOrder(order);
                                    showPlanSeats.get(i).setAvailable(0);
                                    seatRepository.save(showPlanSeats.get(i));
                                    orderSeatsToSet.add(showPlanSeats.get(i));
                                }

                                if (orderSeatsToSet.size() == order.getSeatNum()) {
                                    break;
                                }

                            }

                            if (orderSeatsToSet.size() < order.getSeatNum()) {
                                //分配失败，退款订单
                                User user = order.getUser();
                                double balance = user.getBalance() + order.getPrice();
                                user.setBalance(balance);
                                userRepository.save(user);

                                order.setState("已退款");
                                order.setSeatNum(0);
                                orderRepository.save(order);

                                List<Seat> seatsToClear = order.getSeats();
                                if (seatsToClear != null && seatsToClear.size() != 0) {
                                    for (Seat seat :
                                            seatsToClear) {
                                        seat.setOrder(null);
                                        seat.setAvailable(1);
                                        seatRepository.save(seat);
                                    }
                                }
                            }
                            order.setSeats(orderSeatsToSet);
                            orderRepository.save(order);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
