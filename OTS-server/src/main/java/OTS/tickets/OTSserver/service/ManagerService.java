package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;

public interface ManagerService {

    /**
     * 经理登录
     *
     * @param password 登录密码
     * @return
     */
    ResultMessageBean signIn(ManagerPasswordBean manager);

    /**
     * 获得当前登录的经理信息
     *
     * @param managerName 经理姓名
     * @return
     */
    ManagerInfoBean getCurrentManager(String managerName);
}
