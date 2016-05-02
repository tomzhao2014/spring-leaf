package com.qiktone.web.controller;



import com.qiktone.entity.Account;
import com.qiktone.entity.vo.ShoppingOrderQuery;
import com.qiktone.repository.AccountRepository;
import com.qiktone.repository.HostRepository;
import com.qiktone.service.AccountService;
import com.qiktone.util.Md5;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by tom on 15/12/22.
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HostRepository hostRepository;


    @ModelAttribute
    public void init(Model model){
       // model.addAttribute("module","home");

    }

    /**
     * 首页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
       // throw new RuntimeException("ceshi");
       /* Account user =(Account) session.getAttribute("user");
        if(user!=null){
            return "redirect:/home";
        }else{
            return "login";
        }*/
        return "redirect:/queryShippingOrder?number=1&department_id=2";
    }
/*
    @RequestMapping(path = "login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping(path = "login",method = RequestMethod.POST)

       public String login(String username,String password){
        password = Md5.GetMD5Code(password);
        Account account = accountRepository.login(username,password);
        if (account!=null){
            session.setAttribute("user",account);
            return "redirect:/home";
        }else{
            return "redirect:/";
       }
    }

    @RequestMapping(path = "home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(path = "logout")
    public String logout(){
        session.invalidate();
        return "login";
    }
*/

    @RequestMapping(path = "queryShippingOrder",method = RequestMethod.GET)
    public String query(@RequestParam("number") String number,@RequestParam("department_id") Long departmentId){

        ShoppingOrderQuery result = null;
        if (number!=null && departmentId!=null){
            List<ShoppingOrderQuery> results = hostRepository.queryShippingOrder(number, departmentId);
            if(results!=null && results.size()>0){
                result = results.get(0);
            }
        }
        model.addAttribute("result",result);
        return "query";
    }
}
