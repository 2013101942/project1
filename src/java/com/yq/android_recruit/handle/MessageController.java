package com.yq.android_recruit.handle;


import com.yq.android_recruit.pojo.PersonCompany;
import com.yq.android_recruit.service.IPersonCompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/message.do")
public class MessageController {
    @Resource(name = "personCompany")
    private IPersonCompanyService personCompanyService;

    //发送留言(pc端，只有通过的才能发送留言)
    @RequestMapping("/pc_send")
    public String pcSending(@RequestParam("personId") String personId, @RequestParam("companyId") String companyId,@RequestParam("resumeName") String resumeName,@RequestParam("msg") String msg, HttpServletRequest request){
         personCompanyService.sendMsgPC(personId, companyId,resumeName, msg);
        return "/Resume.do/checkSuccessResume";
    }
    //发送留言（App）
    @RequestMapping(value = "/app_send/{personId}/{companyId}/{recruitId}/{msg}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String appSending(@PathVariable String personId, @PathVariable String companyId,@PathVariable String recruitId, @PathVariable String msg){
        System.out.println("App发送消息...");
        System.out.println(personId);
        System.out.println(companyId);
        System.out.println(recruitId);
        System.out.println(msg);
         personCompanyService.sendMsgApp(personId, companyId,recruitId, msg);
        return "send success";
    }

    //PC查询已发送的留言div简略版(PC端)
    @RequestMapping("/pc_sended")
    public String getPcSendMessage(HttpSession session,HttpServletRequest request){
        List<PersonCompany> msgs = personCompanyService.getPcSendMsg((String) session.getAttribute("userId"));
        request.setAttribute("msgs",msgs);
        return "/send_msg.jsp";
    }
    //App查看已发送的信息
    @RequestMapping("/app_sended/{personId}")
    @ResponseBody
    public List<PersonCompany> getAppSendedMessage(@PathVariable String personId){
        System.out.println("查看已发送的信息personId....."+personId);
        List<PersonCompany> appSendedMsgList = personCompanyService.getAppSendedMsg(personId);
        return appSendedMsgList;
    }

    //PC查看已收到的留言
    @RequestMapping("/pc_received")
    public String getPcReceivedMessage(HttpSession session,HttpServletRequest request){
        List<PersonCompany> msgs = personCompanyService.getPcReceivedMsg((String) session.getAttribute("userId"));
        request.setAttribute("msgs",msgs);
        return "/received_msg.jsp";
    }

    //App查询留言div（App）
    @RequestMapping("/app_received/{personId}")
    @ResponseBody
    public List<PersonCompany> getAppReceivedMessage(@PathVariable String personId){
        List<PersonCompany> appMsg = personCompanyService.getAppReceivedMsg(personId);
        return appMsg;
    }


}
