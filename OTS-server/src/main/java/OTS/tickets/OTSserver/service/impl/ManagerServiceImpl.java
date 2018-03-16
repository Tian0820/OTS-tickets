package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Manager;
import OTS.tickets.OTSserver.repository.ManagerRepository;
import OTS.tickets.OTSserver.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    private ManagerInfoBean managerToManagerInfoBean(Manager manager) {
        return new ManagerInfoBean(manager.getId(), manager.getManagerName(), manager.getPassword());
    }

    @Override
    public ResultMessageBean signIn(ManagerPasswordBean manager) {
        Manager findManager = managerRepository.findManagerByManagerName(manager.getManagerName());
        ResultMessageBean resultMessageBean = new ResultMessageBean(false);
        if (findManager == null) {
            resultMessageBean.message = "经理不存在！";
        } else if (!manager.getPassword().equals(findManager.getPassword())) {
            resultMessageBean.message = "密码错误！";
        } else {
            resultMessageBean.message = findManager.getManagerName();
            resultMessageBean.result = true;
        }
        return resultMessageBean;
    }

    @Override
    public ManagerInfoBean getCurrentManager(String managerName) {
        Manager manager = managerRepository.findManagerByManagerName(managerName);
        if (manager != null) {
            return managerToManagerInfoBean(manager);
        } else {
            return null;
        }
    }
}
