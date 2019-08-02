package com.yq.android_recruit.handle;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.service.ICollectionService;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//收藏
@Controller
@RequestMapping("/collection")
public class CollectController {

    @Resource(name = "collection")
    private ICollectionService collectionService;
    //进行收藏(pc端)
    @RequestMapping(value = "/collectResume.do" ,method = RequestMethod.GET)
    public String collectResume(HttpServletRequest request,@RequestParam("statusPage") String statusPage,@RequestParam("personId") String personId,@RequestParam("companyId") String companyId,@RequestParam("resumeName")  String resumeName){
        collectionService.collectPC(personId,companyId,"1",resumeName,"");
        request.setAttribute("collectResult","collect success");
        return "/page.jsp";

    }

    //进行收藏（app端）
    @RequestMapping(value = "/collectRecruit.do/{personId}/{companyId}/{recruitId}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String collectRecruit(@PathVariable String personId,@PathVariable String companyId,@PathVariable String recruitId){
        collectionService.collectApp(personId,companyId,"0","",recruitId);
        return "collect success";
    }

    //取消收藏(PC端)
    @RequestMapping(value = "/cancelCollectResume.do" ,method = RequestMethod.GET)
    public String cancelCollectResume(HttpServletRequest request,HttpSession session,@RequestParam("personId") String personId,@RequestParam("companyId") String companyId,@RequestParam("resumeName") String resumeName){
        collectionService.cancelCollectPC(personId,companyId,"1",resumeName);
        request.setAttribute("cancelCollectResult","cancelCollect success");
        List<DivResume> collectResume = collectionService.getCollectResume((String) session.getAttribute("userId"));
        session.setAttribute("collectResume",collectResume);
        return "/collection.jsp";
    }

    //取消收藏（App端）
    @RequestMapping(value = "/cancelCollectRecruit.do/{personId}/{companyId}/{recruitId}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String cancelCollectRecruit(@PathVariable String personId,@PathVariable String companyId,@PathVariable String recruitId){
        System.out.println("app取消收藏controller层...");
        collectionService.cancelCollectApp(personId,companyId,"0",recruitId);
        return "cancel success";
    }

    //查看收藏的简历 (只有PC端有)
    @RequestMapping("/collectResumeList")
    public String getCollectResumeList(HttpSession session, HttpServletRequest request){
        System.out.println("来到了collectionResumeList");
        List<DivResume> collectResume = collectionService.getCollectResume((String) session.getAttribute("userId"));
        session.setAttribute("collectResume",collectResume);
        return "/collection.jsp";
    }

    //查看收藏了的招聘信息( 只有App端有)
    @RequestMapping(value = "/collectRecruitList/{personId}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<DivAppRecruitInfo> getCollectRecruitList(@PathVariable String personId){
        List<DivAppRecruitInfo> collectRecruit = collectionService.getCollectRecruit(personId);
        return collectRecruit;
    }
}
