package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.service.ManagerService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @ResponseBody
    @RequestMapping(
            value = "/manager/sign-in",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signIn(
            @RequestBody ManagerPasswordBean manager) {
        return managerService.signIn(manager);
    }

    @ResponseBody
    @RequestMapping(
            value = "/manager/me",
            params = {"name"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ManagerInfoBean getCurrentUser(
            @RequestParam(value = "name") String name) {
        return managerService.getCurrentManager(name);
    }

}