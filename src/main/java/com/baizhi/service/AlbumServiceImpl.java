package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dell on 2018/10/25.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumDao albumDao;
    @Override
    public List<Album> getAllAlbum() {
        return albumDao.getAllAlbum();
    }

    @Override
    public void addAlbum(Album album) {
        albumDao.addAlbum(album);
    }
}
