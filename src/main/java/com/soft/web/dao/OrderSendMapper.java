package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Express;
import com.soft.web.entity.Global;
import com.soft.web.entity.Order;
import com.soft.web.entity.OrderQueryParam;
import com.soft.web.vo.OrderSendVO;
import com.soft.web.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface OrderSendMapper extends BaseMapper<Order>{

    public Order selectByOrderId(String orderId);

    public List<OrderSendVO> selectOrderVOS(OrderQueryParam param);

    public Global selectGlobal();

    public int updateStatus(OrderSendVO OrderSendVO);

    public Express selectExpress(String expressName);

}
