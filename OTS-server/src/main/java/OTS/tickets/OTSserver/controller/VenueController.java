package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.service.VenueService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class VenueController {

    @Autowired
    private VenueService venueService;

    /**
     * 场馆注册
     *
     * @param venue 参观信息
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

    @ResponseBody
    @RequestMapping(
            value = "/venue/me",
            params = {"venueCode"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public VenueInfoBean getCurrentUser(
            @RequestParam(value = "venueCode") String venueCode) {
        return venueService.getCurrentVenue(venueCode);
    }


}
