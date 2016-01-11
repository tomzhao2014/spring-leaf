package com.tomweb.web.controller;

import com.tomweb.Entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tom on 15/12/22.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        Member member = new Member();
        return  "index";
    }

    @RequestMapping(name="device")
    public String device(Model model){
        String type = "unknow";
        return "";
    }
}
