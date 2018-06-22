package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.SearchBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<ShowPlan> search(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "starttime") String starttime,
            @RequestParam(value = "endtime") String endtime,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) {
        return searchService.search(keyword, type, city, starttime, endtime, page, size);
    }
}
