package com.qiktone.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by tom on 15/12/22.
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController{


    @ModelAttribute
    public void init(Model model){
        model.addAttribute("module","home");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
       // throw new RuntimeException("ceshi");
        return "login";
    }

    @RequestMapping(path = "login",method = RequestMethod.POST)
       public String login(String username,String password){


        if (session!=null){
            return "redirect:/home";
        }else{
            return "redirect:/home";
       }

    }

    @RequestMapping(path = "home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }


}
