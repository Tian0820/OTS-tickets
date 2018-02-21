package OTS.tickets.OTSserver.controller;

import OTS.tickets.OTSserver.bean.ResultMessageBean;
import OTS.tickets.OTSserver.model.User;
import OTS.tickets.OTSserver.service.UserService;
import OTS.tickets.OTSserver.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            value = "/user/email-verify",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean sendVerifyCode(
            @RequestBody User user) {
        ResultMessage resultMessage = userService.sendEmail(user.getEmail(), user.getUsername());
        ResultMessageBean resultMessageBean = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            resultMessageBean.result = true;
        } else if (resultMessage == ResultMessage.FAILED) {
            resultMessageBean.message = "邮件发送失败！";
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
            @RequestBody User user) {
        ResultMessage resultMessage = userService.signUp(user.getEmail(), user.getUsername(), user.getPhone(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.USER_EXIST) {
            result.message = "该用户已存在！";
        }
        return result;
    }
}
