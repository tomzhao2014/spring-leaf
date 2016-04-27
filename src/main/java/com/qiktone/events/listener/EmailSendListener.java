package com.qiktone.events.listener;

import com.qiktone.events.event.EmailSendEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/4/27 0027
 * Time: 17:03
 */
@Component
public class EmailSendListener implements ApplicationListener<EmailSendEvent> {
    @Override
    @Async
    public void onApplicationEvent(EmailSendEvent event) {


    }
}
