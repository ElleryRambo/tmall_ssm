package com.wgh.tmall.service;

import com.wgh.tmall.pojo.Product;
import com.wgh.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    void init(Product p);

    void update(PropertyValue pv);

    PropertyValue get(int ptid, int pid);

    List<PropertyValue> list(int pid);
}
