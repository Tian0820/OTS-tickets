package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderById(int id);

}
