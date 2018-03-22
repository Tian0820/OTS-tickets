package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

    Venue findVenueByCode(String code);

    Venue findVenueByVenueName(String venueName);

    Venue findVenueById(int id);

}
