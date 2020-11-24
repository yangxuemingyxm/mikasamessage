package com.xingzi.mikasamessage.controller;

import com.alibaba.fastjson.JSON;
import com.xingzi.mikasamessage.entity.*;
import com.xingzi.mikasamessage.service.UserService;
import com.xingzi.mikasamessage.utils.ConstantUtil;
import javafx.scene.chart.PieChart;
import javafx.scene.input.DataFormat;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value="findUserById", method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String ajaxValidate(String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getUserByUserNum(userNum);
        System.out.println(JSON.toJSONString(user));
        return JSON.toJSONString(user);
    }

    @RequestMapping(value="tianjiapengyou",method=RequestMethod.POST)
    public String tianjiapengyou(String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getUserByUserNum(userNum);

        request.setAttribute("haoyouxinxi",user);
       return "haoyouzhuye";
    }
    @RequestMapping(value="tianxiehaoyoushenqing",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String tianxiehaoyoushenqing(String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("haoyouzhanghao",userNum);
        return "tianxiehaoyoushenqing";
    }
    @RequestMapping(value="yanzhengshenqing",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String yanzhengshenqing(String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "tianxiehaoyoushenqing";
    }
    @ResponseBody()
    @RequestMapping(value="addMessage",method=RequestMethod.POST)
    public String addMessage(Integer userId,Integer receiver,Integer typeId,String content, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChatMessage chatMessage=new ChatMessage();
        String str="";
        chatMessage.setSendId(userId.intValue());
        chatMessage.setReceiver(receiver.intValue());
        chatMessage.setTypeId(typeId.intValue());
        chatMessage.setContent(content);




        chatMessage.setMessageDate(new Date());

        int flag=0;
        try{
           flag= userService.addMessage(chatMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(flag>0){
            str="true";
        }else{
            str="false";
        }
        return JSON.toJSONString(str);
    }
    @ResponseBody()
    @RequestMapping(value="like",method=RequestMethod.POST)
    public String like(Integer sayId,String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean str=false;
        Praise praise=new Praise();
        praise.setUserNum(userNum);
        praise.setSayId(sayId.intValue());
        praise.setPraiseDate(new Date());
        int flag=userService.addPraise(praise);
        if(flag>0){
            str=true;
        }else{
            str=false;
        }
        return JSON.toJSONString(str);
    }
    @ResponseBody()
    @RequestMapping(value="delLike",method=RequestMethod.POST)
    public String delLike(Integer sayId,String userNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean str=false;
        Praise praise=new Praise();
        praise.setUserNum(userNum);
        praise.setSayId(sayId.intValue());
        praise.setPraiseDate(new Date());
        int flag=userService.delPraise(praise);
        if(flag>0){
            str=true;
        }else{
            str=false;
        }
        return JSON.toJSONString(str);
    }
    @ResponseBody()
    @RequestMapping(value="addFriend",method=RequestMethod.POST)
    public String addFriend(String userNum,String friendNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean str=false;
        int userId=userService.getUserByUserNum(userNum).getUserId();
        int friendId=userService.getUserByUserNum(friendNum).getUserId();
        Friend friend=new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        int flag=userService.addFriend(friend);
        friend.setFriendId(userId);
        friend.setUserId(friendId);
        int flag2=userService.addFriend(friend);//双向添加好友
        if(flag>0&&flag2>0){
            str=true;
        }else{
            str=false;
        }
        return JSON.toJSONString(str);
    }
    @RequestMapping(value="faBuDongTai", method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String faBuDongTai(List<MultipartFile> file ,String txt, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Say say = new Say();
        Say say1=null;
        Class cls=say.getClass();
        Random random = new Random();
        try {
            //使用反射第一步:获取操作类MethodDemoFieldDemo所对应的Class对象
            //使用MethodDemo类的class对象生成 实例
            Object obj = cls.newInstance();
            say1=(Say) obj;
            int imageNum = 1;
            String hint = "false";
            //判断文件是否为空
            if (!file.isEmpty()) {
                String path = request.getSession().getServletContext().getRealPath("static"+File.separator+"assets"+File.separator+"img"+File.separator+"placeholder");
                System.out.println(path);
                String newFileName=null;
                for (MultipartFile multipartFile : file) {
                    System.out.println(multipartFile.getName());
                    System.out.println(multipartFile.getOriginalFilename());
                    System.out.println(multipartFile.getSize());
                    String oldFileName = multipartFile.getOriginalFilename();//原文件
                    if (multipartFile.getSize()>0){
                        int randint =(int)Math.floor((random.nextDouble()*100000));	//产生0-10000之间随机数
                        newFileName=String.valueOf(System.currentTimeMillis()+randint+oldFileName);
                        String prefix= FilenameUtils.getExtension(newFileName);//原文件后缀
                            File file1 = new File(path, newFileName);
                            if (!file1.exists()) {
                                file1.mkdirs();
                            }
                            multipartFile.transferTo(file1);
                        //获取public int ddResult(int addNum)方法
                        Method setImg = cls.getMethod("setImage" + imageNum, new Class[]{String.class});
                        setImg.invoke(obj, newFileName);
                        System.out.println("===========>"+say1.getImage1());
                        imageNum++;
                    }


                    // TODO: handle exception
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        String content=request.getParameter("content");
        Date sayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date saytime=null;
        try{
           String str=sdf.format(sayDate);
           saytime=sdf.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        say1.setSayDate(saytime);
        say1.setSayContent(content);
        String userNum=((User)request.getSession().getAttribute(ConstantUtil.USER_SESSION)).getUserNum();
        say1.setUserNum(userNum);
        userService.addSay(say1);
        return "redirect:toweiyouquan";
    }
}


