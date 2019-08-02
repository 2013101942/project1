package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.Enterprise;

public interface IEnterpriseDao {
    Enterprise getEnterprise(String eId);
    void updateEnterprise(String userId,String name,String property,String scale,String city,String addr,String tel, String info);
    void setEnterprise(String companyId,String companyName);
}
