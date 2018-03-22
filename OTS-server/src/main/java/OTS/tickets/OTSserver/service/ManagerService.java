package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.ApproveBean;
import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Approval;
import OTS.tickets.OTSserver.model.User;

import java.util.List;

public interface ManagerService {

    /**
     * 经理登录
     *
     * @param manager 登录密码
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

    /**
     * 获得所有审批信息
     *
     * @return 审批列表
     */
    List<Approval> getAllApprovals();

    /**
     * 经理进行审批
     *
     * @param approval 审批
     * @return 审批结果
     */
    ResultMessageBean managerApproval(ApproveBean approval);

    /**
     * 获得所有用户
     *
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 管理员封禁用户
     *
     * @param userId 用户id
     * @return 封禁结果
     */
    ResultMessageBean banUser(int userId);
}
