package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.dao.IEnterpriseDao;
import com.yq.android_recruit.dao.IExperienceDao;
import com.yq.android_recruit.dao.IUserDao;
import com.yq.android_recruit.pojo.User;
import com.yq.android_recruit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理用户表的各种方法
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IEnterpriseDao enterpriseDao;
    //验证用户
    @Override
    public String validateUser(String userName, String password){
        System.out.println("userDao..."+userDao);
        System.out.println("userName"+userName);
        System.out.println("password"+password);
        if (userName == null || userName.equals("")){
            return "用户名为空";
        }else if (password == null || password.equals("")){
            return "密码为空";
        }else {
            boolean isExist = userIsExist(userName);
            if (!isExist ){
                return "用户不存在";
            } else {
                String u_password = userDao.getPassword(userName);
                if (!u_password.equals(password)){
                     return "用户名或密码错误";
                }else {
                    return "success";
                }
            }
        }
    }

    @Override
    public String getUserId(String userName) {
        String userId = userDao.getUserId(userName);
        return userId;
    }
    @Override
    public boolean userIsExist(String userName){
        String userId = userDao.getUserId(userName);
        if (userId == null || "".equals(userId)){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public User getUser(String userId){
        User user = userDao.getUser(userId);
        return user;
    }

    //注册
    @Override
    public void register(String userName,String password,String kind){
        userDao.setUser(userName,password,kind);
        if ("1".equals(kind)){
            //如果是公司，先要创建公司基本信息记录
            enterpriseDao.setEnterprise(getUserId(userName),userName);
        }
    }
}
