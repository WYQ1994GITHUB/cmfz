package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.baizhi.util.File2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dell on 2018/10/24.
 */
@Controller
public class BannerController {
    @Autowired
    private BannerService bannerService;

    //  分页查询轮播图
    @RequestMapping("/getAllBanners") public @ResponseBody
    Map getAllBanners(int rows, int page) {
        Map allBanner = bannerService.getAllBanner(rows, page);
        return allBanner;
    }

    //  添加
    @RequestMapping("/addBanner")
    public @ResponseBody Boolean addBanner(Banner banner, @RequestParam("fileName") MultipartFile file, HttpServletRequest request) {
        // 要上传的目标文件存放路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String localPath = realPath+"upload";
        if (File2Utils.upload(file, localPath, file.getOriginalFilename(), request)) {
            Object newName = request.getAttribute("newName");
            // 上传成功，给出页面提示
            banner.setUrl("upload/"+ newName);
            try {
                bannerService.addBanner(banner);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //  修改
    @RequestMapping("/updateBanner")
    public @ResponseBody
    Boolean updateBanner(Banner banner) {
        try {
            bannerService.updateBanner(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  删除
    @RequestMapping("/deleteteBanner")
    public @ResponseBody
    Boolean deleteteBanner(int id) {
        try {
            bannerService.deleteteBanner(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
