package com.xingzi.mikasamessage.service;

import com.xingzi.mikasamessage.entity.*;

import java.util.List;

public interface UserService {
    /**
     *
     * @param userNum
     * @return
     */
    public User getUserByUserNum(String userNum);
    /**
     * 根据用户id查询出首页消息列表
     * @param sendId
     * @return
     */
    public List<ChatMessage> getChatMessages(int sendId);
    /**
     * 根据id得到用户具体信息
     * @param userId
     * @return
     */
    public User getUser(int userId);
    /**
     * 新增用户
     * @param user
     * @return
     */
    public int addUser(User user);
    /**
     * 查询好友列表
     * @param userId
     * @return
     */
    public List<Friend> getFriendListByUserId(String userId);
    /**
     *根据账号拿到用户对象
     * @param userId
     * @return
     */
    public User getUserByUserId(String userId);

    /**
     * 根据用户账号查询出好友动态列表
     * @param userNum
     * @return
     */
    public List<Say> getSayListByUserNum(String userNum);
    /**
     * 查询动态列表（所有）
     * @return
     */
    public List<Say> getSayList(User user);
    /**
     * 根据动态Id找到此动态对应的赞
     * @param sayId
     * @return
     */
    List<Praise> getPraiseListBySayId(int sayId);

    /**
     * 根据动态id找到此动态对应的评论
     * @param sayId
     * @return
     */
    List<Comment> getCommentListBySayId(int sayId);
    /**
     * 得到所有用户
     * @return
     */
    List<User> getAllUser();
    /**
     * 添加一条动态
     * @param say
     * @return
     */
    int addSay(Say say);
    /**
     * 根据userNum找到动态
     * @param userNum
     * @return
     */
    List<Say> getSayByUserNum(String userNum);
    /**
     * 添加一条消息
     * @param chatMessage
     * @return
     */
    int addMessage(ChatMessage chatMessage);
    /**
     * 添加一条点赞信息
     * @param praise
     * @return
     */
    int addPraise(Praise praise);
    /**
     *
     * 删除一条点赞信息
     * @param praise
     * @return
     */
    int delPraise(Praise praise);
    /**
     * 添加一条好友信息
     * @param friend
     * @return
     */
    int addFriend(Friend friend);
    /**
     * 查询用户与好友之间的聊天信息
     * @param id
     * @return
     */
    List<ChatMessage> getMessageById(int id,int friendId);

}
