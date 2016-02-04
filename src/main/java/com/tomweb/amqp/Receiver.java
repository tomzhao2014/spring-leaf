package com.tomweb.amqp;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tom on 16/1/30.
 */
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
