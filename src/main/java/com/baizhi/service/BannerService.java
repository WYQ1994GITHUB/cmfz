package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dell on 2018/10/24.
 */
public interface BannerService {
    Map getAllBanner(int rows, int page);

    void addBanner(Banner banner);

    void updateBanner(Banner banner);

    void deleteteBanner(int id);
}
