package com.yq.android_recruit.service;

import com.yq.android_recruit.pojo.User;

public interface IUserService {
    String validateUser(String userName, String password);
    String getUserId(String userName);
    boolean userIsExist(String userName);
    User getUser(String userId);
    void register(String userName,String password,String kind);
}
