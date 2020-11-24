package com.xingzi.mikasamessage.interceptor;


import com.xingzi.mikasamessage.entity.User;
import com.xingzi.mikasamessage.utils.ConstantUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        /*if (modelAndView.getViewName().endsWith("login")){
            HttpSession session = httpServletRequest.getSession();
            User user = (User)session.getAttribute(ConstantUtil.USER_SESSION);
            if (user!=null){
                httpServletResponse.sendRedirect("index");
            }
        }*/
    }



    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
