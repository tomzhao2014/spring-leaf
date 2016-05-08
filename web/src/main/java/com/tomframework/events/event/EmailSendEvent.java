package com.tomframework.events.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/4/27 0027
 * Time: 17:02
 */
public class EmailSendEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EmailSendEvent(Object source) {
        super(source);
    }
}
