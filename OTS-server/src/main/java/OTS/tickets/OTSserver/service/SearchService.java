package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.model.ShowPlan;
import org.springframework.data.domain.Page;


public interface SearchService {
    Page<ShowPlan> search(String keyword, String type, String city, String starttime, String endtime, int page, int size);
}
