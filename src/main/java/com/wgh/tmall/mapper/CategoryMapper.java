package com.wgh.tmall.mapper;

import com.wgh.tmall.pojo.Category;
import com.wgh.tmall.util.Page;

import java.util.List;

public interface CategoryMapper {
    //查询所有分类
    List<Category> list(Page page);

    //获取总数据条数
    public int total();

    //增加分类
    void add(Category category);

    //删除分类
    void delete(int id);

    //获取分类
    Category get(int id);

    //编辑分类
    void update(Category category);
}
