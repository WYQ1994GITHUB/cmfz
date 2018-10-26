package com.baizhi.entity;

import java.util.List;

/**
 * Created by dell on 2018/10/23.
 */
public class Menu {
    private int id;
    private String title;
    private int parentId;
    private String iconCls;
    private String url;
    private List<Menu> list;

    public Menu() {
    }

    public Menu(int id, String title, int parentId, String iconCls, String url, List<Menu> list) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.iconCls = iconCls;
        this.url = url;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", parentId=" + parentId +
                ", iconCls='" + iconCls + '\'' +
                ", url='" + url + '\'' +
                ", list=" + list +
                '}';
    }
}
