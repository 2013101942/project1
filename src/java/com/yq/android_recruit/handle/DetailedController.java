package com.yq.android_recruit.handle;

import com.yq.android_recruit.pojo.*;
import com.yq.android_recruit.service.IDetailedService;
import com.yq.android_recruit.service.IEnterpriseService;
import com.yq.android_recruit.service.IPersonCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/detailedInfo.do")
public class DetailedController {

    @Resource(name = "detailed")
    private IDetailedService detailedService;
    @Resource(name = "personCompany")
    private IPersonCompanyService personCompanyService;
    @Resource(name = "enterprise")
    private IEnterpriseService enterpriseService;

    //用户点击某个简历，进入查看详细简历
    @RequestMapping("/resume")
    public String checkResume(String userId , String resumeName, String statusPage, HttpServletRequest request, HttpSession session){
        System.out.println("前端页面是........."+statusPage);
        DetailedResume detailedResume = detailedService.loadDetailedResume(userId, resumeName);
        request.setAttribute("detailedResume",detailedResume);
        //查询本公司所有岗位(参数为公司id)
        List<String> myJobs = detailedService.getMyJobs(String.valueOf(session.getAttribute("userId")));
        session.setAttribute("myJobs",myJobs);
        //查询的不同的简历，显示的页面是不同的
        if ("received_resume.jsp".equals(statusPage)){
            return "/detailedResumeReceived.jsp";
        }else if ("success_resume.jsp".equals(statusPage)){
            return "/detailedResumeSuccess.jsp";
        }else if ("collection.jsp".equals(statusPage)){
            return "/detailedResumeCollected.jsp";
        }else {
            return "/detailedResume.jsp";
        }
    }

    //发送面试邀请
    @RequestMapping("/invitation")
    public String sendInvitation(@RequestParam("statusPage") String statusPage, HttpServletRequest request,@RequestParam("personId") String personId,@RequestParam("companyId") String companyId,@RequestParam("msg") String msg,@RequestParam("resumeName") String resumeName,@RequestParam("job") String job){
        System.out.println("邀请..."+msg);
        personCompanyService.sendInvitation(personId,companyId,"1",msg,resumeName,job);
        request.setAttribute("inviteResult","invite success");
         if ("detailedResumeCollected.jsp".equals(statusPage)){
            return "/collection.jsp";
        }else {
            return "/page.jsp";
        }
    }

    //app查看详细招聘信息
    @RequestMapping("/recruit")
    @ResponseBody
    public RecruitInfo checkRecruit(@RequestParam("companyId") String companyId,@RequestParam("recruitId") String recruitId){
        RecruitInfo recruitInfo = detailedService.loadDetailedRecruit(recruitId, companyId);
        return recruitInfo;
    }

    //app查看公司详细信息
    @RequestMapping("/enterprise")
    @ResponseBody
    public Enterprise getEnterprise(String eId){
        Enterprise enterprise = enterpriseService.getEnterprise(eId);
        return enterprise;
    }

}
