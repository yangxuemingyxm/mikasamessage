package com.xingzi.mikasamessage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingzi.mikasamessage.entity.Message;
import com.xingzi.mikasamessage.entity.User;
import com.xingzi.mikasamessage.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String UserRegister(String userNum, String nickname,String password, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, BadHanyuPinyinOutputFormatCombination {
        User userByUserNum = userService.getUserByUserNum(userNum);
        String str= DigestUtils.md5DigestAsHex(password.getBytes());
        HanyuPinyinOutputFormat pyFormat = new        HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = nickname.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], pyFormat);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
       String initial= output;
        User user=new User();
        user.setUserNum(userNum);
        user.setNickname(nickname);
        user.setPassword(str);

        user.setInitial(initial);
        int i = userService.addUser(user);
        PrintWriter out = response.getWriter();
        if (i<0){
            return "register";
        }else{
            //out.print("<script> alert('注册成功,即将跳转到登陆页面');location.href='login.jsp'</script>");
            //return "login";
            return "login";
        }
    }
    @ResponseBody
    @RequestMapping(value="findUser", method=RequestMethod.POST)
    public String ajaxValidate(String userNum,HttpServletRequest request,HttpServletResponse response) throws IOException {
        User user = userService.getUserByUserNum(userNum);//从数据库查找前端出来的用户
        String msg;
        if(user==null){//user为空，说明没有找到该用户，可以注册。反之。
            msg= "yes";//然后返回给前端执行success方法。
        }else{
            msg= "no";
        }
        System.out.println(msg);
        return msg;
    }
}
