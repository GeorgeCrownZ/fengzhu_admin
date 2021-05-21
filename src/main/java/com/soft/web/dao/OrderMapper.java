package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderQueryParam;
import com.soft.web.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<OrderVO> orderlist(OrderQueryParam param);
}
