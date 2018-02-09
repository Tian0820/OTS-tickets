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
     * @param to
     * @param content
     * @return
     */
    public ResultMessage sendRegisterMail(String to, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("message = " + message);
        message.setFrom(from);
        System.out.println("from = " + from);
        message.setTo(to);
        System.out.println("to = " + to);
        message.setText(content);
        System.out.println("content = " + content);
        message.setSubject("11111");
        System.out.println("subject = " + "11111");

        try {
            mailSender.send(message);
            System.out.println("message = " + message);
            logger.info("简单邮件已发送");
        } catch (Exception e) {
            logger.error("邮件发送时异常", e);
        }
        return ResultMessage.SUCCESS;
    }
}