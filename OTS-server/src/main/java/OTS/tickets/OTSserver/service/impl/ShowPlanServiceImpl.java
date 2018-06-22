package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.service.ShowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<ShowPlan> getAllShowPlans(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<ShowPlan> p = showPlanRepository.findAll(pageable);

        return p;
    }

    @Override
    public ShowPlan getShowPlanById(int showPlanId) {
        return showPlanRepository.findShowPlanByIdOrderBySeatsAsc(showPlanId);
    }
}
