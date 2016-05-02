package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.*;
import com.qiktone.entity.vo.CompanyQuery;
import com.qiktone.repository.ConstantRepository;
import com.qiktone.repository.DomainRepository;
import com.qiktone.repository.HostRepository;
import com.qiktone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
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

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private HostRepository hostRepository;


/*
    @ModelAttribute
    public void init(Model model){
        List<Constant> companyTypes = constantRepository.findByType("company_type");
        List<Constant> companyStateTypes = constantRepository.findByType("company_state_type");
        List<Constant> appTypes = constantRepository.findByType("app_type");
        List<Domain> domains = domainRepository.findAll();
        List<Host> hosts = hostRepository.findAll();
        model.addAttribute("companyTypes",companyTypes);
        model.addAttribute("companyStateTypes",companyStateTypes);
        model.addAttribute("appTypes",appTypes);
        model.addAttribute("module","company");
        model.addAttribute("domains",domains);
        model.addAttribute("hosts",hosts);
    }



    @RequestMapping(method = RequestMethod.GET)
    public String index(CompanyQuery companyQuery,Integer pageNo,Model model) {
        if(!companyQuery.isEmpty()){
            session.setAttribute("query",companyQuery);
        }
        if(companyQuery.isEmpty()){
            companyQuery=(CompanyQuery)session.getAttribute("query");
        }
        Page page = new Page<Company>(pageNo);
        companyService.list(page,companyQuery);
        model.addAttribute("page",page);
        return "company/index";
    }



    @RequestMapping(path = "/add")
    public String add(){
        return "company/add";
    }



    @RequestMapping(method = RequestMethod.POST)
    public String create(Company company) {
        companyService.create(company);
        return "redirect:/company";
    }


    @RequestMapping(path = "/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        Company company = companyService.getById(id);
        model.addAttribute("company",company);
        return "/company/edit";
    }



    public void update(Company company) {
        companyService.update(company);
    }


    @RequestMapping(path = "/del/{id}")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/company";
    }*/
}
