package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderById(int id);

    List<Order> findOrderByState(String state);

}
