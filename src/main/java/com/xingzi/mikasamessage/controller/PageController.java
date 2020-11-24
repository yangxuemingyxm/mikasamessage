package com.xingzi.mikasamessage.controller;

import com.xingzi.mikasamessage.entity.*;
import com.xingzi.mikasamessage.service.UserService;
import com.xingzi.mikasamessage.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class PageController {
    @Autowired
    private  UserService userService;
    @RequestMapping(value = "toRegister", method = RequestMethod.GET)
    public String toRegisterPage(){
        return "register";
    }
    @RequestMapping(value = "toDanLiaoPage", method = RequestMethod.GET)
    public String toDanLiaoPage( String friendId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
        User friend = userService.getUser(Integer.parseInt(friendId));
        List<ChatMessage> messageList=userService.getMessageById(user.getUserId(),Integer.parseInt(friendId));
        request.setAttribute("messageList",messageList);
        request.setAttribute("friend",friend);
        request.setAttribute("friendId",friend.getUserId());
        request.setAttribute("friendNum",friend.getUserNum());
        return "danliao";
    }

    @RequestMapping(value = "toTianJiaPengYouPage", method = RequestMethod.GET)
    public String toTianJiaPengYouPage( String friendId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "tianjiapengyou";
    }
    @RequestMapping(value = "togerenxinxi", method = RequestMethod.GET)
    public String togerenxinxi( String friendId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "gerenxinxi";
    }
    @RequestMapping(value = "toHaoYouXinXi", method = RequestMethod.GET)
    public String toHaoYouXinXi( String userNum, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUserByUserNum(userNum);
        request.setAttribute("haoyou",user);
        List<Say> sayList=userService.getSayByUserNum(userNum);
        request.setAttribute("sayList",sayList);
        return "haoyouxinxi";
    }
    @RequestMapping(value = "toFaBiaoDongTai", method = RequestMethod.GET)
    public String toFaBiaoDongTai( String friendId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "fabiaodongtai";
    }
    @RequestMapping(value = "toweiyouquan", method = RequestMethod.GET)
    public String toweiyouquan( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //动态传输数据
        User user = (User)request.getSession().getAttribute(ConstantUtil.USER_SESSION);
        List<User> friendList = userService.getAllUser();
        List<Say> sayList=userService.getSayList(user);//得到全部动态
        List<User> userFriendList=new ArrayList<User>();//得到用户好友列表
        HashMap<Integer,List<Praise>> praiseMap=new HashMap<Integer, List<Praise>>();//存放指定动态的点赞列表
        Set<User> praiseUserList=new HashSet<User>();//存放点赞的用户具体信息
        HashMap<Integer,List<Comment>> commentMap=new HashMap<Integer, List<Comment>>();//存放指定动态的评论列表
        Set<User> commentUserList=new HashSet<User>();//存放评论的用户具体信息

        for (Say say : sayList) {
            if (say.getUserNum().equals(user.getUserNum())){
                List<Praise> praiseListForSay= userService.getPraiseListBySayId(say.getSayId());//得到指定动态的点赞列表
                praiseMap.put(say.getSayId(),praiseListForSay);
                List<Comment> commentList = userService.getCommentListBySayId(say.getSayId());
                commentMap.put(say.getSayId(),commentList);
                userFriendList.add(user);
                continue;
            }
            for (User user1 : friendList) {
                if (say.getUserNum().equals(user1.getUserNum())){
                    List<Praise> praiseListForSay= userService.getPraiseListBySayId(say.getSayId());//得到指定动态的点赞列表
                    praiseMap.put(say.getSayId(),praiseListForSay);
                    List<Comment> commentList = userService.getCommentListBySayId(say.getSayId());
                    commentMap.put(say.getSayId(),commentList);
                    userFriendList.add(user1);
                    break;
                }

            }

        }
        for (Integer friend : praiseMap.keySet()) {
            List<Praise> praiseList=praiseMap.get(friend);
            for (Praise praise : praiseList) {
                User userByUserNum = userService.getUserByUserNum(praise.getUserNum());
                boolean add = praiseUserList.add(userByUserNum);
                System.out.println(add);
            }

        }
        for (Integer friend : commentMap.keySet()) {
            List<Comment> commentList=commentMap.get(friend);
            for (Comment comment : commentList) {
                User userByUserNum = userService.getUserByUserNum(comment.getUserNum());
                commentUserList.add(userByUserNum);
            }

        }
        request.setAttribute("commentUserList",commentUserList);//评论用户列表
        request.setAttribute("praiseUserList",praiseUserList);//点赞用户列表
        request.setAttribute("praiseMap",praiseMap);//点赞列表集合
        request.setAttribute("commentMap",commentMap);//评论列表集合
        request.setAttribute("userFriendList",userFriendList);//用户好友列表
        request.setAttribute("sayList",sayList);//动态集合列表
        return "weiyouquan";
    }
}
