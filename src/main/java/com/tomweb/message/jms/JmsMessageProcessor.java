package com.tomweb.message.jms;

/**
 * JmsMessageProcessor
 *
 * @author Tom Zhao
 * @date 2015/12/28 0028
 */
public abstract interface JmsMessageProcessor<M extends JmsMessage>
{
    public abstract void processMessage(M paramM);
}
