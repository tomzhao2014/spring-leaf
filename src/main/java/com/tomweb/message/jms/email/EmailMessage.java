package com.tomweb.message.jms.email;

import com.tomweb.message.jms.AbstractJmsMessage;
import com.tomweb.message.jms.JmsMessage;
import com.tomweb.message.jms.JmsMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 15/12/28.
 */
public class EmailMessage extends AbstractJmsMessage implements JmsMessage
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = 2560813563987905249L;
    public static final String DEFAULT_FORMAT = "text/html;charset=gb2312";
    private String fromUser;
    private String fromAddress;
    private List<String> toAddresses = new ArrayList();
    private List<String> ccAddresses = new ArrayList();
    private String title;
    private String content;
    private EmailSendFailureHandler handler;
    private Object params;
    private String format = "text/html;charset=gb2312";

    public void addAddress(String address)
    {
        if (isValidAddress(address))
            this.toAddresses.add(address);
    }

    public void addUser(String userEmail)
    {
        if (userEmail == null) {
            this.logger.warn("userEmail is null!");
            return;
        }
        this.toAddresses.add(userEmail);
    }

    public void setContent(String template, Map<String, String> param)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(template);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer templateContent = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null)
            {

                templateContent.append(line);
            }
            String tmpContent = templateContent.toString();
            for (String key : param.keySet()) {
                String value = (String)param.get(key);
                if (value != null) {
                    tmpContent = tmpContent.replace(key, value);
                }
            }
            this.content = tmpContent;
        } catch (IOException e) {
            this.logger.error("读取邮件模板错误!", e);
        }
    }

    private boolean isValidAddress(String address)
    {
        return true;
    }

    public String toString()
    {
        return "EmailMessage [getClass()=" + getClass() + "]";
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getToAddresses() {
        return this.toAddresses;
    }

    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public String getAddress() {
        if (this.toAddresses != null) {
            return this.toAddresses.toString();
        }
        return null;
    }

    public List<String> getCcAddresses() {
        return this.ccAddresses;
    }

    public void setCcAddresses(List<String> ccAddresses) {
        this.ccAddresses = ccAddresses;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public JmsMessageProcessor<EmailMessage> getMessageProcessor()
    {
        return EmailMessageProcessor.getInstance();
    }

    public EmailSendFailureHandler getHandler() {
        return this.handler;
    }

    public void setHandler(EmailSendFailureHandler handler) {
        this.handler = handler;
    }

    public Object getParams() {
        return this.params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}