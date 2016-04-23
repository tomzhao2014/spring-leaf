package com.qiktone.web.controller;

import com.qiktone.entity.*;
import com.qiktone.repository.*;
import com.qiktone.service.AccountService;
import com.qiktone.service.RoleService;
import com.qiktone.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by tom on 16/3/5.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{
    @Autowired
    private AccountRepository accountRepository;

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
     *
     * @param model
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public String index(Long id,Model model) {
        Account user = (Account)session.getAttribute("user");
        List<Role> roles = null;
        List<Account> accounts = null;
        if(id!=null){
            accounts = accountRepository.findByCompay(id);
            roles = roleRepository.getRoleByCompany(id);
        }
        List<Company> companys = companyRepository.findAll();
        model.addAttribute("companys",companys);
        model.addAttribute("roles",roles);
        model.addAttribute("accounts",accounts);
        return "account/index";
    }


    @RequestMapping(path = "/list/{cid}",method = RequestMethod.POST)
    public @ResponseBody List<Account> list(@PathVariable("cid") Long cid){
        session.setAttribute("currentCompanyId",cid);
        List<Account>  accounts = accountRepository.findByCompay(cid);
        return accounts;
    }

    /**
     *
     * @return
     */
    @RequestMapping(path = "/add")
    public String add(){
        return "account/add";
    }


    /**
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String   create(Account account) {
        Long companyId = (Long)session.getAttribute("currentCompanyId");
        account.setCompanyId(companyId);
        account.setPassword(Md5.GetMD5Code(account.getPassword()));
        accountService.create(account);

        return "redirect:/account";
    }

    /**
     * a
     * @param id
     * @return
     */
    @RequestMapping(path = "/edit/{id}")
    public String edit(@PathVariable Long id){
        Account account = accountRepository.getById(id);
        model.addAttribute("account",account);
        return "/account/edit";
    }


    /**
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(Account account) {
        accountRepository.update(account);
        return "redirect:/account";
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
