package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.PasswordBean;
import OTS.tickets.OTSserver.bean.UserInfoBean;
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

    @Autowired
    CodeUtil codeUtil;

    private UserInfoBean userToUserInfoBean(User user) {
        return new UserInfoBean(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getLevel(), user.getPoint());
    }

    @Override
    public ResultMessage signIn(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            // 用户不存在
            return ResultMessage.USER_NOT_EXIST;
        } else if (!user.getPassword().equals(password)) {
            // 密码错误
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
    public ResultMessage sendEmail(String email, String username) {
        User oldEmail = userRepository.findUserByEmail(email);
        User oldUsername = userRepository.findUserByUsername(username);
        if (oldEmail != null || oldUsername != null) {
            // 用户已存在，验证失败
            return ResultMessage.USER_EXIST;
        }
        String encryptCode = codeUtil.encryptCode(email); // 用户邮箱加密
        // 发送验证码
        if (mailUtil.sendRegisterMail(email, encryptCode).equals(ResultMessage.SUCCESS)) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage emailVerification(String email, String code) {
        String encryptCode = codeUtil.encryptCode(email); // 用户邮箱加密
        if (code.equals(encryptCode)) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage signUp(String email, String username, String phone, String password) {
        User oldEmail = userRepository.findUserByEmail(email);
        User oldUsername = userRepository.findUserByUsername(username);
        if (oldEmail != null || oldUsername != null) {
            // 用户已存在，验证失败
            return ResultMessage.USER_EXIST;
        }
        User user = new User(username, password, email, phone, 0, 0.0);
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
    public UserInfoBean getCurrentUser(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return userToUserInfoBean(user);
        }
        return null;
    }
}
