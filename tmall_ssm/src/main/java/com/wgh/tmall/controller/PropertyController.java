package com.wgh.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgh.tmall.pojo.Category;
import com.wgh.tmall.pojo.Property;
import com.wgh.tmall.service.CategoryService;
import com.wgh.tmall.service.PropertyService;
import com.wgh.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_add")
    public String add(Model model, Property p){
        propertyService.add(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id){
        Property p = propertyService.get(id);
        Category c =categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p){
        propertyService.update(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page){
        Category c =categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps =propertyService.list(cid);

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        //拼接字符串"&cid="+c.getId()，设置给page对象的Param值。
        // 因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",c);
        model.addAttribute("page",page);

        return "admin/listProperty";
    }
}
