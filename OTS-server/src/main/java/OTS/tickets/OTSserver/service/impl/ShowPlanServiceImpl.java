package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.service.ShowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowPlanServiceImpl implements ShowPlanService {

    @Autowired
    ShowPlanRepository showPlanRepository;

//    private ShowPlanBean ShowPlanToShowPlanBean(ShowPlan showPlan) {
//
//    }

    @Override
    public List<ShowPlan> getAllShowPlans() {
        return showPlanRepository.findAll();
    }

    @Override
    public ShowPlan getShowPlanById(int showPlanId) {
        return showPlanRepository.findShowPlanByIdOrderBySeatsAsc(showPlanId);
    }
}
