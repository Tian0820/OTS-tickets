package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    Order findOrderById(int id);

    List<Order> findOrderByState(String state);

    List<Order> findOrderByType(String type);

    List<Order> findOrderByCreateTimeStartingWith(String createTime);

}
