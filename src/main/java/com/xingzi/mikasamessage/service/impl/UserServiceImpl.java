package com.xingzi.mikasamessage.service.impl;

import com.xingzi.mikasamessage.dao.UserDao;
import com.xingzi.mikasamessage.entity.*;
import com.xingzi.mikasamessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public User getUserByUserNum(String userNum) {
        return userDao.getUserByUserNum(userNum);
    }

    public List<ChatMessage> getChatMessages(int sendId) {
        return userDao.getChatMessages(sendId);
    }

    public User getUser(int userId) {
        return userDao.getUser(userId);
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public List<Friend> getFriendListByUserId(String userId) {
      return  userDao.getFriendListByUserId(userId);
    }

    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }
    public List<Say> getSayListByUserNum(String userNum) {
        return userDao.getSayListByUserNum(userNum);
    }

    public List<Say> getSayList(User user) {
        return userDao.getSayList(user);
    }
    public List<Praise> getPraiseListBySayId(int sayId) {
        return userDao.getPraiseListBySayId(sayId);
    }

    public List<Comment> getCommentListBySayId(int sayId) {
        return userDao.getCommentListBySayId(sayId);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
    public int addSay(Say say) {
        return userDao.addSay(say);
    }

    public  List<Say> getSayByUserNum(String userNum) {
        return userDao.getSayByUserNum(userNum);
    }

    public int addMessage(ChatMessage chatMessage) {
        return userDao.addMessage(chatMessage);
    }

    public int addPraise(Praise praise) {
        return userDao.addPraise(praise);
    }

    public int delPraise(Praise praise) {
        return userDao.delPraise(praise);
    }

    public int addFriend(Friend friend) {
        return userDao.addFriend(friend);
    }
    public List<ChatMessage> getMessageById(int id, int friendId) {
        return userDao.getMessageById(id,friendId);
    }
}
