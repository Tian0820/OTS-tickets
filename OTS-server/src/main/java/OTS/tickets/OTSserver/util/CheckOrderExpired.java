package OTS.tickets.OTSserver.util;

import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.Seat;
import OTS.tickets.OTSserver.repository.OrderRepository;
import OTS.tickets.OTSserver.repository.SeatRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CheckOrderExpired {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SeatRepository seatRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void check() {
        List<Order> orders = orderRepository.findOrderByState("未付款");
        if ((orders != null) && (!orders.isEmpty())) {
            for (Order order : orders) {
                Date now = new Date();
                try {
                    Date createDate = df.parse(order.getCreateTime());
                    long interval = (now.getTime() - createDate.getTime()) / (1000 * 60);
                    if (interval > 15) {
                        List<Seat> seats = order.getSeats();
                        for (int i = 0; i < seats.size(); i++) {
                            Seat seat = seatRepository.findSeatById(seats.get(i).getId());
                            seat.setAvailable(1);
                            seat.setOrder(null);
                            seatRepository.save(seat);
                        }
                        order.setState("已过期");
                        orderRepository.save(order);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
