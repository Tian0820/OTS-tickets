package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShowPlanService {

    Page<ShowPlan> getAllShowPlans(int page, int size);

    ShowPlan getShowPlanById(int showPlanId);
}
