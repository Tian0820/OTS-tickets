package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Seat findSeatById(int id);
}
