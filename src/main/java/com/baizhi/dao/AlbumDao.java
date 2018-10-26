package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

/**
 * Created by dell on 2018/10/25.
 */
public interface AlbumDao {
    List<Album> getAllAlbum();

    void addAlbum(Album album);
}
