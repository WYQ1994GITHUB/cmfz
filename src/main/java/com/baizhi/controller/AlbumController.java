package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.util.File2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dell on 2018/10/25.
 */
@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("/getAllAlbum")
    public @ResponseBody List<Album> getAllAlbum(){
        return albumService.getAllAlbum();
    }
//    添加
    @RequestMapping("/addAlbum")
    public @ResponseBody Boolean addAlbum(Album album, @RequestParam("fileName") MultipartFile file, HttpServletRequest request){
        // 要上传的目标文件存放路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String localPath = realPath+"upload";
        if (File2Utils.upload(file, localPath, file.getOriginalFilename(), request)) {
            Object newName = request.getAttribute("newName");
            // 上传成功，给出页面提示
            album.setCoverImg("upload/"+ newName);
            try {
                albumService.addAlbum(album);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
