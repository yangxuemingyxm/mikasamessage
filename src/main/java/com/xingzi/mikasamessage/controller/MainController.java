package com.xingzi.mikasamessage.controller;

import com.alibaba.fastjson.JSON;
import com.xingzi.mikasamessage.entity.ChatMessage;
import com.xingzi.mikasamessage.entity.Friend;
import com.xingzi.mikasamessage.entity.User;
import com.xingzi.mikasamessage.service.UserService;
import com.xingzi.mikasamessage.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String goMain(HttpServletRequest request, HttpServletResponse response, Model model){
        User user=(User)request.getSession().getAttribute(ConstantUtil.USER_SESSION);
        //得到聊天信息列表
        List<ChatMessage> chatMessages= userService.getChatMessages(user.getUserId());
        List<User> userList=new ArrayList<User>();
        for (ChatMessage message : chatMessages) {
            System.out.println("发送人"+message.getSendId()+"接收人"+message.getReceiver()+"发送内容"+message.getContent());
            User user1 =null;
            //如果用当前登陆用户id等于发送人id就取收信人信息，如果当前登陆用户id等于收信人id就取发信人信息
            user1=user.getUserId()==message.getSendId()?userService.getUser(message.getReceiver()):userService.getUser(message.getSendId());

            System.out.println("用户名"+user1.getUserNum());
            userList.add(user1);
        }
        request.getSession().setAttribute(ConstantUtil.USER_CHAT_MESSAGES,chatMessages);
        request.getSession().setAttribute("jsonlist", JSON.toJSONString(userList));
        request.getSession().setAttribute(ConstantUtil.USER_LIST,userList);
        /*得到好友列表*/
        //根据传来的昵称得到相应的userId
        //根据userId查询出好友列表
        List<Friend> friendList = userService.getFriendListByUserId(String.valueOf(user.getUserId()));
        //存放具体好友信息的集合
        List<User> map=new ArrayList<User>();
        //存放好友昵称的拼音
        String pinyinlist[]=new String[friendList.size()];
        int i=0;
        //得到具体的好友信息   把好友的昵称的拼音存放进数组进行排序
        for (Friend friend : friendList) {
            User user1 = userService.getUserByUserId(String.valueOf(friend.getFriendId()));
            pinyinlist[i]=user1.getInitial();
            i++;
            map.add(user1);
        }
        for (String s : pinyinlist) {
            System.out.println(s);
        }
        Arrays.sort(pinyinlist);
        for (String s : pinyinlist) {
            System.out.println(s);
        }
        List<String> pylist = Arrays.asList(pinyinlist);
        request.setAttribute("friendMap",map);
        request.setAttribute("pylist",pylist);
        return "index";
    }
}
