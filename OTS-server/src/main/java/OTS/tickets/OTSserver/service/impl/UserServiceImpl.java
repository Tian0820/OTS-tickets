package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.PasswordBean;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.repository.UserRepository;
import OTS.tickets.OTSserver.service.UserService;
import OTS.tickets.OTSserver.util.CodeUtil;
import OTS.tickets.OTSserver.util.MailUtil;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailUtil mailUtil;

    @Override
    public ResultMessage signIn(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            // 登录失败
            return ResultMessage.FAILED;
        } else {
            // 登录成功
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage signOut() {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage resetPassword(PasswordBean passwordBean) {
        return null;
    }

    @Override
    public ResultMessage signUp(String email, String username, String phone, String password) {
        User oldEmail = userRepository.findUserByEmail(email);
        User oldUsername = userRepository.findUserByUsername(username);
        if (oldEmail != null || oldUsername != null) {
            // 用户已存在，注册失败
            return ResultMessage.USER_EXIST;
        }
        // 注册新用户，初始等级为0，初始积分为0
        CodeUtil codeUtil = new CodeUtil();
        String code = codeUtil.encryptCode(email); // 用户邮箱加密
        User user = new User(username, password, email, phone, 0, 0.0);
        user.setActivate(false); // 设置用户为未激活状态
        user.setCode(code); // 设置用户激活码

        if (mailUtil.sendRegisterMail(email, code).equals(ResultMessage.SUCCESS)) {

        }

        userRepository.save(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage userCheckMail(String email, String code) {
        User user = userRepository.findUserByCode(code);
        if (user.getEmail() != null && user.getEmail().equals(email)) {
            user.setActivate(true);
            userRepository.save(user);
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public ResultMessage updateUser(User user) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(int id) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}
