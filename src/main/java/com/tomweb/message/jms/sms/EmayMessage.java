package com.tomweb.message.jms.sms;

/**
 * Created by tom on 15/12/28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.tomweb.message.jms.AbstractJmsMessage;
import com.tomweb.message.jms.JmsMessage;
import com.tomweb.message.jms.JmsMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmayMessage extends AbstractJmsMessage implements JmsMessage
{
    private static final long serialVersionUID = 2993858183120320601L;
    public static final String MESSAGE_PREFIX = "【泛微】";
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private String[] receivers;
    private String content;

    public String[] getReceivers()
    {
        return this.receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public String getContent() {
        return "【泛微】" + this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(String template, Map<String, String> param)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(template);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer templateContent = new StringBuffer();
            String line;
            while ((line=br.readLine()) != null)
            {

                templateContent.append(line);
            }
            String tmpContent = templateContent.toString();
            for (String key : param.keySet()) {
                String value = (String)param.get(key);
                tmpContent = tmpContent.replace(key, value);
            }
            this.content = tmpContent;
        } catch (IOException e) {
            this.logger.error("读取短消息模板错误!", e);
        }
    }

    public JmsMessageProcessor<EmayMessage> getMessageProcessor()
    {
        return EmayMessageProcessor.getInstance();
    }
}
