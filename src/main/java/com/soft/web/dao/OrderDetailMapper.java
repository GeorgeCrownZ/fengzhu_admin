package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderDetail;
import com.soft.web.entity.OrderLog;
import com.soft.web.vo.FaOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetail> orderDetailList(String pid);

    public Order selectOrderById(@Param("id")Long id);

    public List<OrderDetail> selectOrderDetailById(@Param("oid")Long oid);

    public int updateOrderStatus(@Param("id")Long id);
}
