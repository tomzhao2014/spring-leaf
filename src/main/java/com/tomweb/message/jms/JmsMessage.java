package com.tomweb.message.jms;

import java.io.Serializable;

/**
 * JmsMessage
 *
 * @author Tom Zhao
 * @date 2015/12/28 0028
 */
public abstract interface JmsMessage extends Serializable
{
    public abstract <M extends JmsMessage> JmsMessageProcessor<M> getMessageProcessor();
}