package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2018/10/24.
 */
public interface BannerDao {
    public List<Banner> getAllBanner(@Param("start") int start, @Param("rows") int rows);
    public int getCount();

    void addBanner(Banner banner);

    void updateBanner(Banner banner);

    void deleteteBanner(int id);
}
