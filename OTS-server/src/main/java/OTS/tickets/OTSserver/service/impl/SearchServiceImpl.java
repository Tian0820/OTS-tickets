package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.SearchBean;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.repository.ShowPlanRepository;
import OTS.tickets.OTSserver.repository.VenueRepository;
import OTS.tickets.OTSserver.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ShowPlanRepository showPlanRepository;

    @Override
    public Page<ShowPlan> search(String keyword, String type, String city, String starttime, String endtime, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "time");
        List<ShowPlan> showPlans = showPlanRepository.findAll();
        Iterator<ShowPlan> iterator = showPlans.iterator();
        while (iterator.hasNext()) {
            ShowPlan showPlan = iterator.next();

            if (null != city && !"".equals(city) && !showPlan.getVenue().getCity().equals(city)) {
                iterator.remove();
                continue;
            }

            if (null != keyword && !"".equals(keyword) && !showPlan.getName().contains(keyword)) {
                iterator.remove();
                continue;
            }
            if (null != starttime && null != endtime && !"".equals(starttime) && !"".equals(endtime)) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date start = dateFormat.parse(starttime);
                    Date end = dateFormat.parse(endtime);
                    Date date = dateFormat.parse(showPlan.getTime());

                    if (start.getTime() > date.getTime() && end.getTime() < date.getTime()) {
                        iterator.remove();
                        continue;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (null != type && !"".equals(type) && !type.equals(showPlan.getType())) {
                iterator.remove();
                continue;
            }
        }


//        Page<ShowPlan> p = showPlanRepository.findByCityAndKeyword(city, keyword, new Specification<ShowPlan>() {
//            @Override
//            public Predicate toPredicate(Root<ShowPlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                if (null != type && !"".equals(type)) {
//                    list.add(criteriaBuilder.equal(root.get("type").as(String.class), type));
//                }
//
//                if (null != starttime && null != endtime && !"".equals(starttime) && !"".equals(endtime)) {
//                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        Date start = dateFormat.parse(starttime);
//                        Date end = dateFormat.parse(endtime);
//
//                        list.add(criteriaBuilder.between(root.get("time").as(Date.class), start, end));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                Predicate[] p = new Predicate[list.size()];
//
//                return criteriaBuilder.and(list.toArray(p));
//            }
//        }, pageable);

        return new PageImpl<>(showPlans.subList(size * page, Math.min(showPlans.size(),size * page + size)), pageable, showPlans.size());
    }
}
