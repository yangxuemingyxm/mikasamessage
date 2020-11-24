package com.xingzi.mikasamessage.controller;

import com.xingzi.mikasamessage.entity.User;
import com.xingzi.mikasamessage.service.UserService;
import com.xingzi.mikasamessage.utils.ConstantUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"login",""},method = RequestMethod.GET)
    public  String login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public  String login(String userNum, String password, HttpServletRequest request, HttpServletResponse response, Model model){
        User user=userService.getUserByUserNum(userNum);
        if (user==null){
            model.addAttribute("message","用户不存在");
            return login();

        }
        else{
            String UserPassword= DigestUtils.md5DigestAsHex(password.getBytes());
            if (UserPassword.equals(user.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute(ConstantUtil.USER_SESSION,user);
                return "redirect:index";
            }else{
                model.addAttribute("message","密码错误");
                return login();
            }

        }
    }
    @RequestMapping(value = "logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest servletRequest){
        servletRequest.getSession().invalidate();
        return login();
    }

}

