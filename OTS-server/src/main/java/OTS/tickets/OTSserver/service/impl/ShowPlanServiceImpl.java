package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.bean.ShowPlanVO;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.service.ShowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShowPlanServiceImpl implements ShowPlanService {

    @Autowired
    ShowPlanRepository showPlanRepository;

//    private ShowPlanBean ShowPlanToShowPlanBean(ShowPlan showPlan) {
//
//    }

    @Override
    public Page<ShowPlanVO> getAllShowPlans(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "time");
        Page<ShowPlan> p = showPlanRepository.findAll(pageable);

        List<ShowPlanVO> showPlanVOS = new ArrayList<>();

        for (ShowPlan showPlan : p) {
            Set<Double> set = new HashSet<>();
            showPlan.getSeats().forEach(seat -> {
                set.add(seat.getPrice());
            });
            showPlanVOS.add(new ShowPlanVO(showPlan, set));
        }

        return new PageImpl<>(showPlanVOS, pageable, p.getTotalElements());
    }

    @Override
    public ShowPlan getShowPlanById(int showPlanId) {
        return showPlanRepository.findShowPlanByIdOrderBySeatsAsc(showPlanId);
    }
}
