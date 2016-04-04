package com.qiktone.web.controller;

import com.qiktone.core.orm.mybatis.Page;
import com.qiktone.entity.Company;
import com.qiktone.entity.Constant;
import com.qiktone.entity.Domain;
import com.qiktone.entity.Host;
import com.qiktone.entity.vo.CompanyQuery;
import com.qiktone.repository.ConstantRepository;
import com.qiktone.repository.DomainRepository;
import com.qiktone.repository.HostRepository;
import com.qiktone.service.CompanyService;
import com.qiktone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;

    @Autowired
    private ConstantRepository constantRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private HostRepository hostRepository;



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

    /**
     *
     * @param pageNo
     * @param model
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public String index(CompanyQuery companyQuery,Integer pageNo,Model model) {

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

        return "redirect:/company";
    }

    /**
     * a
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(path = "/edit/{id}")
    public String edit(@PathVariable Long id,Model model){


        return "/company/edit";
    }


    /**
     *
     * @param company
     */
    public void update(Company company) {

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/del/{id}")
    public String delete(@PathVariable Long id) {

        return "redirect:/company";
    }
}
