package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.*;
import OTS.tickets.OTSserver.model.*;
import OTS.tickets.OTSserver.repository.ApprovalRepository;
import OTS.tickets.OTSserver.repository.ManagerRepository;
import OTS.tickets.OTSserver.repository.UserRepository;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.ManagerService;
import OTS.tickets.OTSserver.service.VenueService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VenueService venueService;

    private ManagerInfoBean managerToManagerInfoBean(Manager manager) {
        return new ManagerInfoBean(manager.getId(), manager.getManagerName(), manager.getPassword());
    }

    @Override
    public ResultMessageBean signIn(ManagerPasswordBean manager) {
        Manager findManager = managerRepository.findManagerByManagerName(manager.getManagerName());
        ResultMessageBean resultMessageBean = new ResultMessageBean(false);
        if (findManager == null) {
            resultMessageBean.message = "经理不存在！";
        } else if (!manager.getPassword().equals(findManager.getPassword())) {
            resultMessageBean.message = "密码错误！";
        } else {
            resultMessageBean.message = findManager.getManagerName();
            resultMessageBean.result = true;
        }
        return resultMessageBean;
    }

    @Override
    public ManagerInfoBean getCurrentManager(String managerName) {
        Manager manager = managerRepository.findManagerByManagerName(managerName);
        if (manager != null) {
            return managerToManagerInfoBean(manager);
        } else {
            return null;
        }
    }

    @Override
    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    @Override
    public ResultMessageBean managerApproval(ApproveBean approvalBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        Approval approval = approvalRepository.findApprovalById(approvalBean.getApprovalId());

        if (approval == null) {
            result.message = "不存在对应审批！";
        } else {
            approval.setState(approvalBean.getApprovalResult());
            approvalRepository.save(approval);
            if (approval.getType().equals("修改")) {
                Venue venue = approval.getVenue();
                venueRepository.save(venue);
            }
            result.result = true;
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResultMessageBean banUser(int userId) {
        User user = userRepository.findUserById(userId);
        user.setActivate(0);
        userRepository.save(user);
        return new ResultMessageBean(true);
    }

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public List<VenueStatisticsBean> getAllVenueStatistics() {
        List<Venue> venues = getAllVenues();
        List<VenueStatisticsBean> venueStatistics = new ArrayList<>();

        for (Venue venue : venues) {
            List<Order> orders = venueService.getVenueOrders(venue.getCode());
            venueStatistics.add(new VenueStatisticsBean(venue.getVenueName(), venue.getCode(), venue.getBalance(),
                    venue.getCity() + " " + venue.getAddress(), orders, venue.getShowPlans()));
        }
        return venueStatistics;
    }
}
