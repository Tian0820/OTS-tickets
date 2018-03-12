package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.VenueInfoBean;
import OTS.tickets.OTSserver.util.ResultMessage;

public interface VenueService {

    /**
     * 场馆登录
     *
     * @param code     场馆编码
     * @param password 密码
     * @return 当前登录状态
     */
    ResultMessage signIn(String code, String password);

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return
     */
    ResultMessage signUp(VenueInfoBean venue);


}
