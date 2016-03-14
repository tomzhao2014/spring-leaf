package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Constant;
import com.qiktone.repository.ConstantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tom on 2016/3/10.
 */
@RequestMapping("/constant")
@Controller
public class ConstantController extends BaseController{

    @Autowired
    private ConstantRepository constantRepository;
    @Override
    @RequestMapping(path = "",method = RequestMethod.GET)
    public String index(Model model) {
        Page<Constant> page = new Page<Constant>();
        List<Constant> constantList = constantRepository.findAll(page);
        System.out.println(constantList);
        System.out.println(page);
        model.addAttribute("page",page);
        model.addAttribute("list",constantList);
        return "constant/index";
    }
}
