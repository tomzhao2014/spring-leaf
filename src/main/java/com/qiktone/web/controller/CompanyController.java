package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Company;
import com.qiktone.entity.Constant;
import com.qiktone.repository.ConstantRepository;
import com.qiktone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ConstantRepository constantRepository;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @ModelAttribute
    public void init(Model model){
        List<Constant> companyTypes = constantRepository.findByType("company_type");
        List<Constant> companyStateTypes = constantRepository.findByType("company_state_type");
        List<Constant> appTypes = constantRepository.findByType("app_type");


    }

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
