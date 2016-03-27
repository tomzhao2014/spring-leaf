package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Constant;
import com.qiktone.repository.ConstantRepository;
import com.qiktone.service.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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


    @ModelAttribute
    public void init(Model model){
        model.addAttribute("module","constant");
    }

    /**
     *
     * @param pageNo
     * @param model
     * @return
     */

    @RequestMapping(path = "",method = RequestMethod.GET)
    public String index(Integer pageNo,Constant constant,Model model) {
        Page page = new Page<Constant>(pageNo);
        constantService.list(page,constant);
        model.addAttribute("page",page);
        return "constant/index";
    }

    /**
     *
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(){
        return "constant/add";
    }


    /**
     *
     * @param constant
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(Constant constant) {
        constantService.create(constant);
        return "redirect:/constant";
    }

    /**
     * a
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(path = "/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        Constant constant = constantService.getById(id);
        model.addAttribute("constant",constant);
        return "/constant/edit";
    }


    /**
     *
     * @param constant
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(Constant constant) {
        constantService.update(constant);
        return "redirect:/constant";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/del/{id}")
    public String delete(@PathVariable Long id) {
        constantService.delete(id);
        return "redirect:/constant";
    }
}
