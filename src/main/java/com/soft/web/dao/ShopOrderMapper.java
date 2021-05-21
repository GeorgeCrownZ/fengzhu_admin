package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.Shop;
import com.soft.web.entity.ShopOrderQueryParam;
import com.soft.web.vo.ShopOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopOrderMapper extends BaseMapper<Order> {

    public List<Shop> selectAllShops();

    public List<ShopOrderVO> selectOrders(ShopOrderQueryParam shopOrderQueryParam);

}
