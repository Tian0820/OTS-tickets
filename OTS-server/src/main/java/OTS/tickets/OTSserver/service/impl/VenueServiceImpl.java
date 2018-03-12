package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.model.Venue;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    @Override
    public ResultMessage signIn(String code, String password) {
        return null;
    }

    @Override
    public ResultMessage signUp(VenueInfoBean venue) {
        Venue oldVenue = venueRepository.findVenueByVenueName(venue.getVenue_name());
        if (oldVenue != null) {
            return ResultMessage.VENUE_EXIST;
        } else {
            System.out.println(Timestamp.valueOf(df.format(new Date()))); // 2015-06-25 14:27:41.0
            String code = "";
            venueRepository.save(new Venue(code, venue.getVenue_name(), venue.getCity(), venue.getAddress(),
                    venue.getSeat_type(), venue.getPassword()));
        }


        return null;
    }
}
