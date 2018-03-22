package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.ApproveBean;
import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Approval;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.service.ManagerService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @RequestMapping(
            value = "/manager/approvals",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<Approval> getAllApprovals() {
        return managerService.getAllApprovals();
    }

    @ResponseBody
    @RequestMapping(
            value = "/manager/approve",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean managerApprove(
            @RequestBody ApproveBean approval) {
        return managerService.managerApproval(approval);
    }

    @ResponseBody
    @RequestMapping(
            value = "/manager/users",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<User> getAllUsers() {
        return managerService.getAllUsers();
    }

    @ResponseBody
    @RequestMapping(
            value = "/manager/ban-user/{userId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean getAllUsers(@PathVariable int userId) {
        return managerService.banUser(userId);
    }

}
