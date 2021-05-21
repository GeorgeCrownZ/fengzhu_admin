package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Member;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberOrdersMapper extends BaseMapper<Member> {

    public List<Order> selectOrdersByUserid(@Param("userId")Long userId);

    public List<OrderDetail> selectByOrderId(@Param("orderId")String orderId);
}
