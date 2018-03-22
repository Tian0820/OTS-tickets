package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.ShowPlanBean;
import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.bean.VenuePasswordBean;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.ShowPlan;
import OTS.tickets.OTSserver.util.ResultMessage;

import java.util.List;

public interface VenueService {

    /**
     * 场馆登录
     *
     * @param venuePasswordBean 场馆编码和密码
     * @return 当前登录状态
     */
    ResultMessageBean signIn(VenuePasswordBean venuePasswordBean);

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return
     */
    ResultMessageBean signUp(VenueInfoBean venue);

    /**
     * 获得当前登录的场馆信息
     *
     * @param venueCode 场馆编号
     * @return
     */
    VenueInfoBean getCurrentVenue(String venueCode);

    /**
     * 场馆发布演出计划
     *
     * @param showPlanBean 演出信息
     * @return 发布结果
     */
    ResultMessageBean uploadShowPlan(ShowPlanBean showPlanBean);

    /**
     * 获得场馆所有演出计划
     *
     * @param venueCode 场馆编号
     * @return 演出计划列表
     */
    List<ShowPlan> getVenueShowPlans(String venueCode);

    /**
     * 获得场馆所有订单
     *
     * @param venueCode 场馆编号
     * @return 订单列表
     */
    List<Order> getVenueOrders(String venueCode);


}
