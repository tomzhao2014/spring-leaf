package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Constant;
import com.qiktone.repository.ConstantRepository;
import com.qiktone.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tom on 2016/3/10.
 */
@RequestMapping("/constant")
@Controller
public class ConstantController extends BaseController{

    @Autowired
    private ConstantService constantService;
    @Override
    @RequestMapping(path = "",method = RequestMethod.GET)
    public String index(Integer pageNo,Model model) {
        Page page = new Page<Constant>(pageNo);
        constantService.list(page);
        model.addAttribute("page",page);
        return "constant/index";
    }
    @RequestMapping(path = "/add")
    public String add(){
        return "constant/add";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(Constant constant) {
        constantService.create(constant);
        return "redirect:/constant";
    }

    @RequestMapping(path = "/edit")
    public String edit(Long id){
        return "edit";
    }


    public void update(Constant constant) {
        constantService.update(constant);
    }
}
