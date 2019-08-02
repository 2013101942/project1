package com.yq.android_recruit.service.impl;


import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.dao.IEnterpriseDao;
import com.yq.android_recruit.pojo.Enterprise;
import com.yq.android_recruit.service.IEnterpriseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enterprise")
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Autowired
    private IEnterpriseDao enterpriseDao;

    //查看公司详细信息
    @Override
    public Enterprise getEnterprise(String eId) {
        System.out.println("企业id............."+eId);
        Enterprise enterprise = enterpriseDao.getEnterprise(eId);
        return enterprise;
    }

    //编辑公司基本信息
    @Override
    public void updateEnterprise( String userId, String name, String property, String scale, String city, String addr, String tel, String info) {
        System.out.println("注册公司...."+name);
        enterpriseDao.updateEnterprise( userId,name,property,scale,city,addr,tel,info);
    }

}
