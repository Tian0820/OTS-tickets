package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.bean.VenuePasswordBean;
import OTS.tickets.OTSserver.model.Seat;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.model.Venue;
import OTS.tickets.OTSserver.repository.SeatRepository;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    ShowPlanRepository showPlanRepository;

    @Autowired
    SeatRepository seatRepository;

    private VenueInfoBean venueToVenueInfoBean(Venue venue) {
        return new VenueInfoBean(venue.getId(), venue.getCode(), venue.getVenueName(), venue.getCity(),
                venue.getAddress(), venue.getSeatType(), venue.getPassword());
    }

    @Override
    public ResultMessageBean signIn(VenuePasswordBean venuePasswordBean) {
        Venue venue = venueRepository.findVenueByCode(venuePasswordBean.getCode());
        ResultMessageBean result = new ResultMessageBean(false);
        if (venue == null) {
            result.message = "场馆编号不存在！";
        } else if (!venue.getPassword().equals(venuePasswordBean.getPassword())) {
            result.message = "密码错误！";
        } else {
            result.result = true;
            result.message = venue.getCode();
        }
        return result;
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
        Venue venue = venueRepository.findVenueByCode(venueCode);
        if (venue != null) {
            return venueToVenueInfoBean(venue);
        }
        return null;
    }

    @Override
    public ResultMessageBean uploadShowPlan(ShowPlanBean showPlanBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        ShowPlan oldShowPlan = showPlanRepository.findShowPlanByName(showPlanBean.getName());

        if (oldShowPlan != null) {
            result.message = "演出名称已存在！";
        } else {
            String[] prices = showPlanBean.getPrice().split(";");
            List<Seat> seats = new ArrayList<>();
            Venue venue = venueRepository.findVenueByCode(showPlanBean.getVenueCode());
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < 100; j++) {
                    Seat seat = new Seat(String.valueOf(i + 1), j, 1, Double.valueOf(prices[i]));
                    seats.add(seat);
                    seatRepository.save(seat);
                }
            }
            showPlanRepository.save(new ShowPlan(showPlanBean.getName(), showPlanBean.getStar(),
                    showPlanBean.getTime(), showPlanBean.getType(), showPlanBean.getIntroduction(),
                    seats, venue));
            result.result = true;
        }
        return result;
    }
}
