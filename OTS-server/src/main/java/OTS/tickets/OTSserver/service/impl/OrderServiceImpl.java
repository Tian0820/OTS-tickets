package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.OrderCreateBean;
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
import java.text.SimpleDateFormat;
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
    public ResultMessageBean createOrder(OrderCreateBean order) {
        ResultMessageBean result = new ResultMessageBean(true);

        User user = userRepository.findUserById(order.getUserId());
        ShowPlan showPlan = showPlanRepository.findShowPlanByIdOrderBySeatsAsc(order.getShowId());

        Date date = new Date();

        System.out.println(df.format(date));

        Order newOrder = new Order(showPlan, user, order.getType(),
                order.getState(), df.format(date), null, order.getPrice(), order.getSeats());
        orderRepository.save(newOrder);

        List<Seat> seatList = order.getSeats();
        for (int i = 0; i < seatList.size(); i++) {
            System.out.print(seatList.get(i).getId() + ";");
            Seat seat = seatRepository.findSeatById(seatList.get(i).getId());
            seat.setAvailable(0);
            seat.setOrder(newOrder);
            seatRepository.save(seat);
        }

        return result;

    }
}
