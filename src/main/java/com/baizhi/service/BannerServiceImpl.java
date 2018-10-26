package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/10/24.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao bannerDao;


    @Override
    public Map getAllBanner(int rows, int page) {
        Map map = new HashMap();
        int start = (page-1)*rows;
        List<Banner> list = bannerDao.getAllBanner(start, rows);
        int count = bannerDao.getCount();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

    @Override
    public void addBanner(Banner banner) {
        bannerDao.addBanner(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDao.updateBanner(banner);
    }

    @Override
    public void deleteteBanner(int id) {
        bannerDao.deleteteBanner(id);
    }
}
