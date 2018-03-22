package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.ApproveBean;
import OTS.tickets.OTSserver.bean.ManagerInfoBean;
import OTS.tickets.OTSserver.bean.ManagerPasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.Approval;
import OTS.tickets.OTSserver.model.Manager;
import OTS.tickets.OTSserver.repository.ApprovalRepository;
import OTS.tickets.OTSserver.repository.ManagerRepository;
import OTS.tickets.OTSserver.service.ManagerService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

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

    @Override
    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    @Override
    public ResultMessageBean managerApproval(ApproveBean approvalBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        Approval approval = approvalRepository.findApprovalById(approvalBean.getApprovalId());

        if (approval == null) {
            result.message = "不存在对应审批！";
        } else {
            approval.setState(approvalBean.getApprovalResult());
            approvalRepository.save(approval);
            result.result = true;
        }
        return result;
    }
}
