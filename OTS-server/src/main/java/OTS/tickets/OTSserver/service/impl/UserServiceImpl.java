package OTS.tickets.OTSserver.service.impl;

import OTS.tickets.OTSserver.bean.PasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.UserCouponBean;
import OTS.tickets.OTSserver.bean.UserInfoBean;
import OTS.tickets.OTSserver.model.Coupon;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.repository.CouponRepository;
import OTS.tickets.OTSserver.repository.UserRepository;
import OTS.tickets.OTSserver.service.UserService;
import OTS.tickets.OTSserver.util.CodeUtil;
import OTS.tickets.OTSserver.util.MailUtil;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    MailUtil mailUtil;

    @Autowired
    CodeUtil codeUtil;

    private UserInfoBean userToUserInfoBean(User user) {
        return new UserInfoBean(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getLevel(), user.getPoint(), user.getAccumulativePoint(),
                user.getBalance(), user.getConsume());
    }

    @Override
    public ResultMessageBean signIn(String email, String password) {
        ResultMessageBean result = new ResultMessageBean(false);
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            // 用户不存在
            result.message = "用户不存在！";
        } else if (!user.getPassword().equals(password)) {
            // 密码错误
            result.message = "密码错误！";
        } else if (user.getActivate() != 1) {
            // 密码错误
            result.message = "您已被注销！";
        } else {
            // 登录成功
            result.result = true;
        }
        return result;
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
        User user = new User(username, password, email, phone, 0, 0.0, 0.0, 1, 10000.0, 0.0);
        userRepository.save(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage userCheckMail(String email, String code) {
        User user = userRepository.findUserByCode(code);
        if (user.getEmail() != null && user.getEmail().equals(email)) {
            user.setActivate(1);
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
    public ResultMessage updateUser(UserInfoBean user) {
        User currentUser = userRepository.findUserByEmail(user.getEmail());
        if (currentUser != null) {
            currentUser.setPhone(user.getPhone());
            currentUser.setUsername(user.getUsername());
            userRepository.save(currentUser);
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessageBean editUserPassword(PasswordBean passwordBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        User user = userRepository.findUserById(passwordBean.getId());
        if (user == null) {
            result.message = "用户不存在！";
        } else if (!user.getPassword().equals(passwordBean.getOldPassword())) {
            result.message = "旧密码错误！";
        } else {
            user.setPassword(passwordBean.getNewPassword());
            userRepository.save(user);
            result.result = true;
        }
        return result;
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

    @Override
    public List<Order> getUserOrders(int userId) {
        User user = userRepository.findUserById(userId);
        return user.getOrders();
    }

    @Override
    public ResultMessageBean exchangeCoupon(UserCouponBean userCouponBean) {
        ResultMessageBean result = new ResultMessageBean(false);
        User user = userRepository.findUserById(userCouponBean.getUserId());
        if (user == null) {
            result.message = "用户不存在！";
        } else {
            Coupon coupon = couponRepository.findCouponByDiscount(userCouponBean.getDiscount());

            double point = user.getPoint() - userCouponBean.getPoint();

            if (point < 0) {
                result.message = "积分不足！";
                return result;
            }

            user.setPoint(point);
            List<Coupon> coupons = user.getCoupons();
            coupons.add(coupon);
            user.setCoupons(coupons);
            userRepository.save(user);

            List<User> users = coupon.getUsers();
            users.add(user);
            coupon.setUsers(users);
            couponRepository.save(coupon);

            result.result = true;
        }
        return result;
    }

    @Override
    public List<Coupon> getUserCoupons(int userId) {
        User user = userRepository.findUserById(userId);
        return user.getCoupons();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
