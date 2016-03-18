package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Company;
import com.qiktone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{
    @Autowired
    private CompanyService companyService;

    /**
     *
     * @param pageNo
     * @param model
     * @return
     */
    @Override
    @RequestMapping(path = "",method = RequestMethod.GET)
    public String index(Integer pageNo,Model model) {
        Page page = new Page<Company>(pageNo);
        companyService.list(page);
        model.addAttribute("page",page);
        return "company/index";
    }

    /**
     *
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(){
        return "company/add";
    }


    /**
     *
     * @param company
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(Company company) {
        companyService.create(company);
        return "redirect:/company";
    }

    /**
     * a
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(path = "/edit/{id}")
    public String edit(Long id,Model model){
        Company company = companyService.getById(id);
        model.addAttribute("company",company);
        return "/company/edit";
    }


    /**
     *
     * @param constant
     */
    public void update(Company company) {
        companyService.update(company);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/del/{id}")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/company";
    }
}
