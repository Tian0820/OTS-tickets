package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.bean.VenuePasswordBean;
import OTS.tickets.OTSserver.util.ResultMessage;

public interface VenueService {

    /**
     * 场馆登录
     *
     * @param venuePasswordBean     场馆编码和密码
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
     * @param venueCode 场馆编号
     * @return
     */
    VenueInfoBean getCurrentVenue(String venueCode);


}
