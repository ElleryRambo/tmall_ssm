package com.wgh.tmall.service.impl;

import com.wgh.tmall.mapper.OrderMapper;
import com.wgh.tmall.pojo.Order;
import com.wgh.tmall.pojo.OrderExample;
import com.wgh.tmall.pojo.User;
import com.wgh.tmall.service.OrderService;
import com.wgh.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Order> user) {
        for (Order order : user) {
            setUser(order);
        }
    }

    public void setUser(Order o) {
        User u = userService.get(o.getUid());
        o.setUser(u);
    }
}
