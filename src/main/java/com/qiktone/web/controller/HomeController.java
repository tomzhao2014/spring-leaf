package com.qiktone.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


/**
 * Created by tom on 15/12/22.
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController{



//    @RequestMapping(method = RequestMethod.GET)
//    public String index(){
//        return  "login";
//    }
//
//    @RequestMapping(name = "login",method = RequestMethod.POST)
//    public String login(String username,String password){
//        System.out.println("loging ");
//
//
//
//        if (session!=null){
//            return "index";
//        }else{
//            return "index";
//        }
//
//    }

    @RequestMapping(name="test",method = RequestMethod.GET)
    public String test(){
        return "index";
    }

}
