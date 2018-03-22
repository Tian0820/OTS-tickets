package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.*;
import OTS.tickets.OTSserver.model.Coupon;
import OTS.tickets.OTSserver.model.Order;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.service.UserService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 验证邮箱
     *
     * @param user 用户邮箱和用户名
     * @return 是否发送邮件成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/send-email",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean sendVerifyCode(
            @RequestBody User user) {
        ResultMessage resultMessage = userService.sendEmail(user.getEmail(), user.getUsername());
        ResultMessageBean resultMessageBean = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            resultMessageBean.result = true;
        } else if (resultMessage == ResultMessage.USER_EXIST) {
            resultMessageBean.message = "用户名或邮箱已存在！";
        } else if (resultMessage == ResultMessage.FAILED) {
            resultMessageBean.message = "邮件发送失败！";
        }
        return resultMessageBean;
    }

    /**
     * 判断验证码是否正确
     *
     * @param code  用户填写的验证码
     * @param email 用户邮箱
     * @return 是否发送邮件成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/email-verify",
            params = {"email", "code"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean emailVerification(@RequestParam(value = "email") String email,
                                               @RequestParam(value = "code") String code
    ) {
        ResultMessage resultMessage = userService.emailVerification(email, code);
        ResultMessageBean resultMessageBean = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            resultMessageBean.result = true;
        } else if (resultMessage == ResultMessage.FAILED) {
            resultMessageBean.message = "验证码错误！";
        }
        return resultMessageBean;
    }


    /**
     * 注册
     *
     * @param user 用户名和密码
     * @return 注册结果，是否成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/sign-up",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signUp(
            @RequestBody UserRegisterBean user) {
        ResultMessage resultMessage = userService.signUp(user.getEmail(), user.getUsername(), user.getPhone(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.USER_EXIST) {
            result.message = "该用户已存在！";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/sign-in",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signIn(
            @RequestBody UserPasswordBean user) {
        ResultMessage resultMessage = userService.signIn(user.getEmail(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.USER_NOT_EXIST) {
            result.message = "用户不存在！";
        } else if (resultMessage == ResultMessage.FAILED) {
            result.message = "密码错误！";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/me",
            params = {"email"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public UserInfoBean getCurrentUser(
            @RequestParam(value = "email") String email) {
        return userService.getCurrentUser(email);
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/info",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editUserInfo(
            @RequestBody UserInfoBean user) {
        ResultMessage resultMessage = userService.updateUser(user);
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.FAILED) {
            result.message = "修改失败！";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/{userId}/orders",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<Order> getUserOrders(
            @PathVariable int userId) {
        return userService.getUserOrders(userId);
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/exchange-coupon",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean exchangeCoupon(
            @RequestBody UserCouponBean userCouponBean) {
        return userService.exchangeCoupon(userCouponBean);
    }

    @ResponseBody
    @RequestMapping(
            value = "/user/{userId}/coupons",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<Coupon> getUserCoupons(
            @PathVariable int userId) {
        return userService.getUserCoupons(userId);
    }


}
