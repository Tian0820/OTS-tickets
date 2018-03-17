package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.service.ShowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<ShowPlan> getCurrentVenue() {
        return showPlanService.getAllShowPlans();
    }

}
