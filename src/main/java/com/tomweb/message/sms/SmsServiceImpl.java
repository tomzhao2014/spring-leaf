package com.tomweb.message.sms;

import com.tomweb.message.jms.JmsMessageService;
import com.tomweb.message.jms.sms.EmayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 15/12/28.
 */
@Service
public class SmsServiceImpl implements SmsService
{

    @Autowired
    private JmsMessageService jmsMessageService;

    public void regPhoneCheck(String code, String mobile)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(new String[] { mobile });
        Map params = new HashMap();
        params.put("${code}", code);
        emayMessage.setContent("/template/sms/regphonecheck.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendInviteToRemind(String sender, String tenantName, String url, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${sender}", sender);
        params.put("${tenantName}", tenantName);
        params.put("${url}", url);
        emayMessage.setContent("/template/sms/inviteToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendDueToRemind(String tenantName, String dateExpired, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${tenantName}", tenantName);
        params.put("${dateExpired}", dateExpired);
        emayMessage.setContent("/template/sms/dueToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendCheckCodeToRemind(String dateApply, String checkCode, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${dateApply}", dateApply);
        params.put("${checkCode}", checkCode);
        emayMessage.setContent("/template/sms/checkCodeToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendApplyToRemind(String proposer, String tenantName, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${proposer}", proposer);
        params.put("${tenantName}", tenantName);
        emayMessage.setContent("/template/sms/applyToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendAgreeToRemind(String admin, String tenantName, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${admin}", admin);
        params.put("${tenantName}", tenantName);
        emayMessage.setContent("/template/sms/agreeToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendPwdResetToRemind(String account, String newPwd, String[] mobiles)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(mobiles);
        Map params = new HashMap();
        params.put("${account}", account);
        params.put("${newPwd}", newPwd);
        emayMessage.setContent("/template/sms/pwdResetToRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }

    public void sendRehireMessages(String code, String mobile)
    {
        EmayMessage emayMessage = new EmayMessage();
        emayMessage.setReceivers(new String[] { mobile });
        Map params = new HashMap();
        params.put("${tenantName}", code);
        emayMessage.setContent("/template/sms/rehireRemind.txt", params);
        this.jmsMessageService.createMessage(emayMessage);
    }
}
