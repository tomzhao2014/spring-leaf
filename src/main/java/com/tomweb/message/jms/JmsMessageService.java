package com.tomweb.message.jms;

import org.springframework.util.Assert;

import javax.print.attribute.standard.Destination;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2015/12/28 0028
 * Time: 19:23
 */
public class JmsMessageService
{
   private JmsTemplate jmsTemplate;
    private Destination defaultDestination;

    public void createMessage(JmsMessage message)
    {
        produceMessage(this.defaultDestination, message);
    }

    public void createMessage(JmsMessage message, String destination)
    {
        Assert.notNull(destination);
        Destination queueDestination = new ActiveMQQueue(destination);
        produceMessage(queueDestination, message);
    }

    private void produceMessage(Destination destination, JmsMessage message) {
        this.jmsTemplate.convertAndSend(destination, message);
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDefaultDestination(Destination defaultDestination) {
        this.defaultDestination = defaultDestination;
    }
}
