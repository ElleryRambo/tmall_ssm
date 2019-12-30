package com.wgh.tmall.service.impl;

import com.wgh.tmall.mapper.OrderItemMapper;
import com.wgh.tmall.pojo.Order;
import com.wgh.tmall.pojo.OrderItem;
import com.wgh.tmall.pojo.OrderItemExample;
import com.wgh.tmall.pojo.Product;
import com.wgh.tmall.service.OrderItemService;
import com.wgh.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem c) {
        orderItemMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem c) {
        orderItemMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    @Override
    public List list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }

    @Override
    public void fill(Order o) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalnumber = 0;
        for (OrderItem orderItem : ois) {
            total += orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
            totalnumber += orderItem.getNumber();
        }

        o.setTotal(total);
        o.setTotalNumber(totalnumber);
        o.setOrderItems(ois);
    }

    public void setProduct(OrderItem oi) {
        Product p =productService.get(oi.getPid());
        oi.setProduct(p);
    }

    public void setProduct(List<OrderItem> ois){
        for (OrderItem orderItem : ois) {
            setProduct(orderItem);
        }
    }
}
