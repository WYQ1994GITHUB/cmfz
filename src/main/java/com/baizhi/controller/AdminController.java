package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by dell on 2018/10/23.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session,String enCode){
        int a = 10;
        String code = (String) session.getAttribute("enCode");
        if(!enCode.equalsIgnoreCase(code)){
            return "login";
        }
        int b = a;
        Admin adm = adminService.login(admin);
        if(adm!=null){
            session.setAttribute("admin", admin);
            return "redirect:/main/main.jsp";
        }
        return "login";
    }
}
