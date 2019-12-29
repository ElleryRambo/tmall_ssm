package com.wgh.tmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgh.tmall.pojo.User;
import com.wgh.tmall.service.UserService;
import com.wgh.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String List(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<User> us = userService.list();

        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);

        model.addAttribute("us",us);
        model.addAttribute("page",page);

        return "admin/listUser";
    }
}
