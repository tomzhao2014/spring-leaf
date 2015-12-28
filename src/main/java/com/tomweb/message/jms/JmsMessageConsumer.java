package com.tomweb.message.jms;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2015/12/28 0028
 * Time: 19:10
 */
public class JmsMessageConsumer
{
    protected final Log logger = LogFactory.getLog(getClass());

    public void handleMessage(JmsMessage message)
    {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("handleMessage:" + message.toString());
        }
        JmsMessageProcessor processor = message.getMessageProcessor();
        processor.processMessage(message);
    }
}
