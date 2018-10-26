package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.File2Utils;
import com.baizhi.util.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;


/**
 * Created by dell on 2018/10/25.
 */
@Controller
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    public @ResponseBody Boolean addChapter(Chapter chapter, @RequestParam("fileName") MultipartFile file, HttpServletRequest request){
        // 要上传的目标文件存放路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String localPath = realPath+"music";
        if (File2Utils.upload(file, localPath, file.getOriginalFilename(), request)) {
            Object newName = request.getAttribute("newName");
            // 上传成功，给出页面提示
            String s = UUIDUtils.getUUID().toString();
            chapter.setId(s);
            chapter.setUrl("music/"+ newName);
            try {
                chapterService.addChapter(chapter);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping("/download")
    public void download(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath+"/"+fileName);

        System.out.println(realPath+"!!!!!!!!!!!");
        System.out.println(file+"@@@@@@@@@@@@@@@@");
//      附件形式下载
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
//      以流的形式响应出去
        FileUtils.copyFile(file, response.getOutputStream());
    }

}
