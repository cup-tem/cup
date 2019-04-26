package top.mnilsy.cup.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by mnilsy on 19-2-27 下午2:09.
 */
@Component("sendMailUtils")
public class SendMailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public SendMailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public SendMailUtil() {
    }

    @Async
    public void send(String email,String code) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("770502378@qq.com", "推点");
            helper.setSubject("验证码");
            helper.setTo(email);
            String html = "验证码内容";
            helper.setText(html, true);
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
