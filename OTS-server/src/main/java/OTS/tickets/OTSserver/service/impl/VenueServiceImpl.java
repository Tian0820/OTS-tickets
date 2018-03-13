package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.model.Venue;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Override
    public ResultMessage signIn(String code, String password) {
        return null;
    }

    @Override
    public ResultMessageBean signUp(VenueInfoBean venue) {
        Venue oldVenue = venueRepository.findVenueByVenueName(venue.getVenueName());
        ResultMessageBean result = new ResultMessageBean(false);
        if (oldVenue != null) {
            result.message = "场馆名称已被注册！";
            return result;
        } else {
            Venue newVenue = new Venue();
            venueRepository.save(newVenue);
            int id = newVenue.getId();
            String code = String.format("%07d", id);
            newVenue.setCode(code);
            newVenue.setVenueName(venue.getVenueName());
            newVenue.setCity(venue.getCity());
            newVenue.setAddress(venue.getAddress());
            newVenue.setSeatType(venue.getSeatType());
            newVenue.setPassword(venue.getPassword());
            venueRepository.save(newVenue);

            result.result = true;
            result.message = code;
            return result;
        }
    }

    @Override
    public VenueInfoBean getCurrentVenue(String venueCode) {
        return null;
    }
}
