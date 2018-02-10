package OTS.tickets.OTSserver.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送邮件
     *
     * @param to   发送邮件地址
     * @param code 用户加密激活码
     * @return 发送结果
     */
    public ResultMessage sendRegisterMail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("message = " + message);
        message.setFrom(from);
        System.out.println("from = " + from);
        message.setTo(to);
        System.out.println("to = " + to);

        String content = "尊敬的用户您好！欢迎使用OTS线上票务系统，仅需一步完成会员注册！您的激活码为：\n";
        content += code + "\n";
        content += "请复制后返回原页面填写";
        message.setText(content);
        message.setSubject("OTS会员注册验证");

        try {
            mailSender.send(message);
            System.out.println("message = " + message);
            logger.info("邮件已发送");
        } catch (Exception e) {
            logger.error("邮件发送时异常", e);
        }
        return ResultMessage.SUCCESS;
    }
}