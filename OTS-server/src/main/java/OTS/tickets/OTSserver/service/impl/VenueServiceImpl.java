package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.*;
import OTS.tickets.OTSserver.model.*;
import OTS.tickets.OTSserver.repository.ApprovalRepository;
import OTS.tickets.OTSserver.repository.SeatRepository;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    ShowPlanRepository showPlanRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ApprovalRepository approvalRepository;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        } else {
            Approval approval = approvalRepository.findApprovalByVenueId(venue.getId());
            if (!venue.getPassword().equals(venuePasswordBean.getPassword())) {
                result.message = "密码错误！";
            } else if (approval.getState().equals("审批中")) {
                result.message = "场馆注册正在审批中！";
            } else if (approval.getState().equals("失败")) {
                result.message = "场馆注册审批未通过！";
            } else {
                result.result = true;
                result.message = venue.getCode();
            }
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
            newVenue.setBalance(0.0);

            //发布审批
            Approval approval = new Approval("审批中", "注册", df.format(new Date()), newVenue);
            approvalRepository.save(approval);

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
    public ResultMessageBean editVenueInfo(VenueInfoBean venueInfoBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        Venue venue = venueRepository.findVenueByCode(venueInfoBean.getCode());
        if (venue == null) {
            result.message = "场馆不存在！";
        } else {
            venue.setAddress(venueInfoBean.getAddress());
            venue.setVenueName(venueInfoBean.getVenueName());

            Approval approval = new Approval("审批中", "修改", df.format(new Date()), venue);
            approvalRepository.save(approval);

            result.result = true;
        }
        return result;
    }

    @Override
    public ResultMessageBean editVenuePassword(PasswordBean passwordBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        Venue venue = venueRepository.findVenueById(passwordBean.getId());
        if (venue == null) {
            result.message = "场馆不存在！";
        } else if (!venue.getPassword().equals(passwordBean.getOldPassword())) {
            result.message = "旧密码错误！";
        } else {
            venue.setPassword(passwordBean.getNewPassword());
            venueRepository.save(venue);
            result.result = true;
        }
        return result;
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
            ShowPlan newShow = new ShowPlan(showPlanBean.getName(), showPlanBean.getStar(),
                    showPlanBean.getTime(), showPlanBean.getType(), showPlanBean.getIntroduction(),
                    venue);
            showPlanRepository.save(newShow);
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < 100; j++) {
                    Seat seat = new Seat(String.valueOf(i + 1), j, 1, Double.valueOf(prices[i]), null, newShow);
                    seats.add(seat);
                    seatRepository.save(seat);
                }
            }
            result.result = true;
        }
        return result;
    }

    @Override
    public List<ShowPlan> getVenueShowPlans(String venueCode) {
        Venue venue = venueRepository.findVenueByCode(venueCode);
        if (venue != null) {
            return venue.getShowPlans();
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getVenueOrders(String venueCode) {
        Venue venue = venueRepository.findVenueByCode(venueCode);
        if (venue != null) {
            List<ShowPlan> showPlans = venue.getShowPlans();
            List<Order> orders = new ArrayList<>();
            for (ShowPlan show :
                    showPlans) {
                orders.addAll(show.getOrders());
            }
            return orders;
        } else {
            return null;
        }
    }
}
