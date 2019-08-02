package com.yq.android_recruit.service;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.Enterprise;

public interface IEnterpriseService {
    //通过id找企业
    Enterprise getEnterprise(String eId);
    //编辑公司基本信息，添加在注册时在userService中有
    void updateEnterprise(String userId,String name,String property,String scale,String city,String addr,String tel, String info);

}
