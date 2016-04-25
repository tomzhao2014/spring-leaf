package com.qiktone.web.controller;

import com.qiktone.entity.*;
import com.qiktone.repository.*;
import com.qiktone.service.AccountService;
import com.qiktone.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ConstantRepository constantRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;



    @ModelAttribute
    public void init(Model model){

        List<Constant> accountStateTypes = constantRepository.findByType("account_state_type");
        List<Constant> appTypes = constantRepository.findByType("app_type");
        List<Domain> domains = domainRepository.findAll();
        List<Host> hosts = hostRepository.findAll();
        model.addAttribute("status",accountStateTypes);
        model.addAttribute("appTypes",appTypes);
        model.addAttribute("module","account");
        model.addAttribute("domains",domains);
        model.addAttribute("hosts",hosts);
    }

    /**
     *
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public String index(Long id) {
        List<Department> departments = departmentRepository.findAllDepartmentByCompany(id);
        List<Company> companys = companyRepository.findAll();
        model.addAttribute("companys",companys);
        session.setAttribute("currentCompanyId",id);
        model.addAttribute("departments",departments);
        return "department/index";
    }


    @RequestMapping(path = "/list/{cid}",method = RequestMethod.POST)
    public @ResponseBody List<Department> list(@PathVariable("cid") Long cid){

        return null;
    }

    /**
     *
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(){
        return "department/add";
    }


    /**
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String   create(Department department) {


        return "redirect:/department";
    }

    /**
     * a
     * @param id
     * @return
     */
    @RequestMapping(path = "/edit/{id}")
    public String edit(@PathVariable Long id){
        return "/department/edit";
    }


    /**
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(Department department) {

        return "redirect:/department";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/del/{id}")
    public String delete(@PathVariable Long id) {

        return "redirect:/department";
    }


}
