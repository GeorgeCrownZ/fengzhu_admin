package com.soft.web.service;

import com.soft.web.dao.ShopOrderMapper;
import com.soft.web.entity.Order;
import com.soft.web.entity.Shop;
import com.soft.web.entity.ShopOrderQueryParam;
import com.soft.web.vo.ShopOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderService {

    @Autowired
    ShopOrderMapper shopOrderMapper;

    public List<Shop> selectAllShops() {
        return shopOrderMapper.selectAllShops();
    }

    public List<ShopOrderVO> selectOrders(ShopOrderQueryParam shopOrderQueryParam) {
        if("---请选择---".equals(shopOrderQueryParam.getShopId())){
            shopOrderQueryParam.setShopId(null);
        }
        return shopOrderMapper.selectOrders(shopOrderQueryParam);
    }

}
