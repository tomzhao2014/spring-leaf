package com.qiktone.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tom on 2016/4/10.
 */
public class AuthorityInterceptor implements HandlerInterceptor{

    private static final String[] IGNORE_URI = {"/","/login"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if(!url.equals("/")){
            if(request.getSession().getAttribute("user")==null){
                response.sendRedirect(request.getContextPath()+"/");
                return false;
            }else{
                return true;
            }
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
