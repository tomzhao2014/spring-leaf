package com.tomweb.web.controller;

import com.tomweb.Entity.Member;
import com.tomweb.amqp.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by tom on 15/12/22.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Receiver receiver;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        Member member = new Member();

        rabbitTemplate.convertAndSend("spring-amqp", "Hello from RabbitMQ!");
        try {
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "index";
    }

    @RequestMapping(name="device")
    public String device(Model model){
        String type = "unknow";
        return "";
    }
}
