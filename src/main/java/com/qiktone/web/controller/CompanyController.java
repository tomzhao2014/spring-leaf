package com.qiktone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "company/index";
    }
}
