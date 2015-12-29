package com.tomweb.message.email;

import com.tomweb.message.jms.JmsMessageService;
import com.tomweb.message.jms.email.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tom on 15/12/28.
 */
@Service
public class EmailServiceImpl
        implements EmailService
{

    @Autowired
    private JmsMessageService jmsMessageService;

    public void sendValidateEmail(String email, Map<String, String> params)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.addAddress(email);
        emailMessage.setTitle("欢迎您注册泛微eteams，请验证邮箱身份");

        emailMessage.setContent("/template/email/activemail.html", params);
        this.jmsMessageService.createMessage(emailMessage);
    }

    public void sendRehireEmail(String email, Map<String, String> params)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.addAddress(email);
        emailMessage.setTitle("您已被返聘回\"" + (String)params.get("${tenantName}") + "\"");
        emailMessage.setContent("/template/email/rehiremail.html", params);
        this.jmsMessageService.createMessage(emailMessage);
    }

    public void sendCaptchaEmail(String email, Map<String, String> params)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.addAddress(email);
        emailMessage.setTitle("泛微eteams验证码");
        emailMessage.setContent("/template/email/verification.html", params);
        this.jmsMessageService.createMessage(emailMessage);
    }

    public void sendInviteMail(String email, Map<String, String> params)
    {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.addAddress(email);
        emailMessage.setFromAddress("eteams@weaver.com.cn");
        emailMessage.setTitle((String)params.get("${inviter}") + "邀请您加入eteams上的\"" + (String)params.get("${tenantName}") + "\"");

        emailMessage.setContent("/template/email/newmail.html", params);
        this.jmsMessageService.createMessage(emailMessage);
    }
}
