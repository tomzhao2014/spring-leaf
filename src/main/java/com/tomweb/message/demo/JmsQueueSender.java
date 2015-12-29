package com.tomweb.message.demo;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2015/12/29 0029
 * Time: 11:48
 */


public class JmsQueueSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void simpleSend() {
        this.jmsTemplate.send(this.queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello queue world");
            }
        });
    }

    public void sendWithConversion() {
        Map map = new HashMap();
        map.put("Name", "Mark");
        map.put("Age", new Integer(47));
        jmsTemplate.convertAndSend("testQueue", map, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setIntProperty("AccountID", 1234);
                message.setJMSCorrelationID("123-00001");
                return message;
            }
        });
    }
}
