package OTS.tickets.OTSserver.service;

import OTS.tickets.OTSserver.bean.PasswordBean;
import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.bean.UserCouponBean;
import OTS.tickets.OTSserver.bean.UserInfoBean;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.util.ResultMessage;

import java.util.List;

public interface UserService {

    /**
     * 登录
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @return 当前登录状态
     */
    ResultMessage signIn(String email, String password);

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    ResultMessage signOut();

    /**
     * 重置密码
     *
     * @param passwordBean 新密码和旧密码
     * @return 重置密码结果状态
     */
    ResultMessage resetPassword(PasswordBean passwordBean);

    /**
     * 给用户邮箱发送验证邮件
     *
     * @param email    用户邮箱
     * @param username 用户名
     * @return
     */
    ResultMessage sendEmail(String email, String username);

    /**
     * 用户注册时验证邮箱
     *
     * @param email 用户邮箱
     * @param code  用户输入的收到的激活码
     * @return
     */
    ResultMessage emailVerification(String email, String code);

    /**
     * 注册用户
     *
     * @param email    用户邮箱
     * @param username 用户账号
     * @param password 用户密码
     * @param phone    用户手机
     * @return 是否注册成功
     */
    ResultMessage signUp(String email, String username, String phone, String password);

    /**
     * @param email
     * @param code
     * @return
     */
    ResultMessage userCheckMail(String email, String code);

    /**
     * 根据username查找用户
     *
     * @param username
     * @return 查到的用户
     */
    User findUserByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user 新用户
     * @return 是否更新成功
     */
    ResultMessage updateUser(UserInfoBean user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    ResultMessage deleteUser(int id);

    /**
     * 获得当前登录的用户
     *
     * @return 当前登录用户
     */
    UserInfoBean getCurrentUser(String email);

    /**
     * 获得用户订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> getUserOrders(int userId);

    /**
     * 用户兑换优惠券
     * @return 兑换结果
     */
    ResultMessageBean exchangeCoupon(UserCouponBean userCouponBean);

}
