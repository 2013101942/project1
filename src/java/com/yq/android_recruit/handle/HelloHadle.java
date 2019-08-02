package com.yq.android_recruit.handle;

import com.yq.android_recruit.pojo.Enterprise;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Spring MVC

/**
 * 单例的
 * 1. 传递javabean参数
 * 2. 获取jsp内置对象
 * 3. ajax返回javabean的json格式数据
 * 4. 上传，下载
 * 5. restful web-service
 * 6、拦截器
 */

//@RequestMapping("/param)   其中的参数可以用来指定资源文件夹
@RequestMapping("/hello")  //用来处理请求地址映射的注解，可用于类或方法上。
                            // 用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
@Controller //跳转页面，@RestController = @Controller + @ReseponseBody
public class HelloHadle {
     @RequestMapping("/say")
     //value的值通过URL中的 ?value = xx 形式传参，在requestMapping中不用指定
    public String sayHello(String value){
        System.out.println("hello"+value);
//        return "hello";  //默认是请求转发,配置前后缀只能用于请求转发
         return "/hello/hello";
     }

     @RequestMapping("/say2")
     //@RequestParam("aa")指明了参数一定要是aa
    public ModelAndView sayHello2(@RequestParam("aa") String value){
         System.out.println("hello.."+value);
         ModelAndView mv = new ModelAndView();
         mv.setViewName("hello");
         mv.addObject("value",value);
         return mv;
     }
    //@PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：
    // URL 中的 {xxx} 占位符可以通过@PathVariable(“xxx“) 绑定到操作方法的入参中
    @RequestMapping("/say3/{bb}/{cc}/{dd}")
    public ModelAndView sayHello3(@PathVariable("bb") String value1,@PathVariable("cc") String value2,@PathVariable("dd") String value3, @RequestParam("name") String userName){
        System.out.println("hello.."+value1+value2+value3+userName);
        //此处在路径前面加 / ,表示根路径，否则会追加到前面的路径后面
        ModelAndView mv = new ModelAndView("/hello/hello.jsp");
        //此处才是真正的资源路径
//        mv.setViewName();
        //在jsp页面上用 el 表达式获取
        mv.addObject("value1",value1);
        mv.addObject("value2",value2);
        mv.addObject("value3",value3);
        mv.addObject("userName",userName);
        return mv;
    }

    @RequestMapping("/test1")
    public ModelAndView test1( HttpSession session , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("hello");
        System.out.println(modelAndView);
        System.out.println(session);
        System.out.println(request);
        System.out.println(response);
        System.out.println(session.getServletContext());
        return modelAndView;
    }

    @RequestMapping(value = "/test4",method = {RequestMethod.POST})
    @ResponseBody
    public List<Enterprise> test2(@RequestBody Enterprise enterprise){
        List<Enterprise> list = new ArrayList<>();
        Enterprise enterprise1  = new Enterprise();
        Enterprise enterprise2  = new Enterprise();
        list.add(enterprise1);
        list.add(enterprise2);
        return list;
    }
}
