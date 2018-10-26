package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dell on 2018/10/23.
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/getMenus")
    public @ResponseBody List<Menu> getMenus(){
        return menuService.getMenus();
    }
}
