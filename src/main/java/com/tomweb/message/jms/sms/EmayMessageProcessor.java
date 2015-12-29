package com.tomweb.message.jms.sms;

import com.tomweb.message.jms.JmsMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tom on 15/12/28.
 */
public class EmayMessageProcessor
        implements JmsMessageProcessor<EmayMessage>
{
    protected EmayHttpClient emayHttpClient;
    protected final Logger logger = LoggerFactory.getLogger(EmayMessageProcessor.class);

    private static EmayMessageProcessor emayMessageProcessor = new EmayMessageProcessor();

    private EmayMessageProcessor()
    {
        this.emayHttpClient = new EmayHttpClient();
    }

    public static EmayMessageProcessor getInstance()
    {
        return emayMessageProcessor;
    }

    public void processMessage(EmayMessage message)
    {
        try {
            sendMsg(message);
        } catch (Exception e) {
            this.logger.info(e.getMessage());
        }
    }

    protected boolean sendMsg(EmayMessage message)
    {
        String[] receivers = message.getReceivers();
        for (String receiver : receivers) {
            asynSendSMS(receiver, message.getContent());
        }
        return true;
    }

    protected boolean asynSendSMS(String phone, String msg)
    {
        String phoneNo = phone.replaceAll("\\+86", "");
        boolean b = false;
        try {
            b = this.emayHttpClient.asynSendSMS(phoneNo, msg);
            this.logger.info("Send :" + phoneNo + " result:" + b);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return b;
    }
}
