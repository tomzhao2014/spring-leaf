package com.tomweb.web.controller;

import com.tomweb.Entity.Member1;
import org.springframework.stereotype.Controller;
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
        Member1 member = new Member1();
        return  "index";
    }
}
