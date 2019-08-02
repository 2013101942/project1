package com.yq.android_recruit.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class UploadAndDownload {
   final File file = new File("D:\\uploadTest");
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile[] files, String username) throws IOException {
        System.out.println(username);
   //重点，上传的内容都在files里面
        for (MultipartFile f:files) {
            String fileName = UUID.randomUUID().toString();
            System.out.println(f.getOriginalFilename());
            int index = f.getOriginalFilename().lastIndexOf(".");
            fileName +=  f.getOriginalFilename().substring(index);
            System.out.println(fileName);
            //创造内存中的文件
            File myfile = new File(file,fileName);
            //创造硬盘中的文件
            myfile.createNewFile();
            //将二进制图片写入到硬盘文件中
            f.transferTo(myfile);
        }
    return "";
    }
    //restFul风格的传参不能有“.”,用此方法处理就可（其实是用正则表达式处理）
    @RequestMapping("/download/{fileName:.+}")
    @ResponseBody
    public String download(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        File outFile = new File(file,fileName);
        //设置响应头和文件名称（设置key和下载时默认文件名，不适用URLEncode（）时中文会乱码，使用后中文会以百分号展示）
        response.addHeader("Content-Disposition","attachment;filename="+
                URLEncoder.encode(fileName,"utf-8"));
        //设置mime类型
        response.setContentType("application/octet-stream");
        if (file.exists()){
            //把outFile读出去，读到response里去
            InputStream is = new FileInputStream(outFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            //response.getOutputStream()是重点
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] bytes = new byte[1024*512];
            int len = 0;
            while ((len = bufferedInputStream.read(bytes))!= -1){
                bufferedOutputStream.write(bytes,0,len);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
            return "ok";
        }else{
            return "not found";
        }

    }
}
