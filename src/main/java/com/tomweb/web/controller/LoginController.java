package com.tomweb.web.controller;

import com.tomweb.entity.User;
import com.tomweb.security.WebAuthenticationManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/26 0026
 * Time: 16:15
 */

@Controller
@RequestMapping("/")
public class LoginController {

    private Log log = LogFactory.getLog(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "login";
    }


    @RequestMapping("login")
    public String login(User user){

        try {
           AuthenticationManager am = new WebAuthenticationManager();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

        } catch(AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());

        return "index";
    }


}
