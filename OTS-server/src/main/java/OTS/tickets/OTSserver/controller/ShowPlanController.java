package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.service.ShowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ShowPlanController {

    @Autowired
    ShowPlanService showPlanService;


    @ResponseBody
    @RequestMapping(
            value = "/show-plan",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<ShowPlan> getCurrentShow(@RequestParam(value = "page") int page,
                                         @RequestParam(value = "size") int size) {
        return showPlanService.getAllShowPlans(page, size);
    }

    @ResponseBody
    @RequestMapping(
            value = "/show-plan/{showPlanId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ShowPlan getShowPlanById(@PathVariable int showPlanId) {
        return showPlanService.getShowPlanById(showPlanId);
    }

}
