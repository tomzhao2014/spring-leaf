package com.tomweb.web.controller;

import com.tomweb.entity.User;
import com.tomweb.service.UserService;
import com.tomweb.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/4 0004
 * Time: 17:36
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController<User>  {

    @Autowired
    private UserService userService;

    public void  create(User user) {
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        userService.create(user);
    }
}
