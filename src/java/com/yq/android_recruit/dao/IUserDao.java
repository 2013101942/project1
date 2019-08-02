package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.User;

public interface IUserDao {

    /**
     * 根据用户id得到用户
     * @param userId
     * @return
     */
    User getUser(String userId);
    //根据用户名获取密码
    String getPassword(String userName);
    //根据用户名获取id
    String getUserId(String userName);
    //注册
    void setUser(String userName,String password,String kind);
}
