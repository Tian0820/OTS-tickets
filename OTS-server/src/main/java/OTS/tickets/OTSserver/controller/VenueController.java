package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.*;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class VenueController {

    @Autowired
    private VenueService venueService;

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return 注册结果，是否成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/venue/sign-up",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signUp(
            @RequestBody VenueInfoBean venue) {
        return venueService.signUp(venue);
    }

    /**
     * 场馆登录
     *
     * @param venue 场馆信息
     * @return 登录结果，是否成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/venue/sign-in",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signIn(
            @RequestBody VenuePasswordBean venue) {
        return venueService.signIn(venue);
    }

    @ResponseBody
    @RequestMapping(
            value = "/venue/me",
            params = {"venueCode"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public VenueInfoBean getCurrentVenue(
            @RequestParam(value = "venueCode") String venueCode) {
        return venueService.getCurrentVenue(venueCode);
    }

    @ResponseBody
    @RequestMapping(
            value = "/venue/info",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editVenueInfo(
            @RequestBody VenueInfoBean venue) {
        return venueService.editVenueInfo(venue);
    }

    @ResponseBody
    @RequestMapping(
            value = "/venue/password",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editVenuePassword(
            @RequestBody PasswordBean passwordBean) {
        return venueService.editVenuePassword(passwordBean);
    }

    /**
     * 场馆发布计划
     *
     * @param showPlanBean 计划信息
     * @return 登录结果，是否成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/venue/show-plan",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean uploadShowPlan(
            @RequestBody ShowPlanBean showPlanBean) {
        return venueService.uploadShowPlan(showPlanBean);
    }


    @ResponseBody
    @RequestMapping(
            value = "/venue/{venueCode}/show-plans",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<ShowPlan> getVenueShowPlans(
            @PathVariable String venueCode) {
        return venueService.getVenueShowPlans(venueCode);
    }

    @ResponseBody
    @RequestMapping(
            value = "/venue/{venueCode}/orders",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<Order> getVenueOrders(
            @PathVariable String venueCode) {
        return venueService.getVenueOrders(venueCode);
    }

    @ResponseBody
    @RequestMapping(
            value = "/venue/check-ticket",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean checkTicket(
            @RequestParam(value = "orderId") int orderId) {
        return venueService.checkTicket(orderId);
    }


}
