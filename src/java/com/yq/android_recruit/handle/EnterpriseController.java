package com.yq.android_recruit.handle;

import com.yq.android_recruit.pojo.Enterprise;
import com.yq.android_recruit.service.IEnterpriseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

//注册和完善基本信息
@Controller
@RequestMapping("/Enterprise")
public class EnterpriseController {

    @Resource(name = "enterprise")
    private IEnterpriseService enterpriseService;
    //注册,编辑公司基本信息（个人是填写简历）
    @RequestMapping(value = "/updateCompany.do" ,method = RequestMethod.GET)
    public String updateCompany(HttpSession session,HttpServletRequest request, @RequestParam("name") String name,@RequestParam("property") String property,@RequestParam("scale") String scale,@RequestParam("city") String city,@RequestParam("addr") String addr,@RequestParam("tel") String tel,@RequestParam("info") String info){
        String companyId = (String)session.getAttribute("userId");
        System.out.println("保存公司信息...."+name);
        enterpriseService.updateEnterprise(companyId,name,property,scale,city,addr,tel,info);
        Enterprise enterprise = enterpriseService.getEnterprise((String) session.getAttribute("userId"));
        request.setAttribute("saveEnterpriseStatus","save success");
        request.setAttribute("enterprise",enterprise);
        return "/checkEnterprise.jsp";
    }


    //pc端查看公司详细信息
    @RequestMapping("/checkCompany.do")
    public String getEnterprise(HttpSession session, HttpServletRequest request){
        System.out.println("进来了后台.............."+session.getAttribute("userId"));
        Enterprise enterprise = enterpriseService.getEnterprise((String) session.getAttribute("userId"));

        request.setAttribute("enterprise",enterprise);
        return "/checkEnterprise.jsp";
    }
    //App查看公司的信息
    @RequestMapping(value = "/appCheckCompany.do/{companyId}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Enterprise getEnterpriseToApp(@PathVariable String companyId){
        System.out.println("companyId....."+companyId);
        Enterprise enterprise = enterpriseService.getEnterprise(companyId);
        System.out.println(enterprise.getE_name());
        return enterprise;
    }
}
