package com.wgh.tmall.service;

import com.wgh.tmall.pojo.Category;
import com.wgh.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list(Page page);

    int total();

    void add (Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
