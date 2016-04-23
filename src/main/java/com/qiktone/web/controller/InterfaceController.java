package com.qiktone.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tom on 2016/4/23.
 */

@RequestMapping(path = "/")
public class InterfaceController {
    @RequestMapping(path = "table")
    public String table(@RequestParam String ccode,@RequestParam String number){

        return "table";

    }

}
