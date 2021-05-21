package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.ShopMapper;
import com.soft.web.entity.Shop;
import com.soft.web.util.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shop")
public class ShopService {
    @Resource
    private ShopMapper shopMapper;

    public List<Shop> getlist(Boolean blank){
        List<Shop> list = shopMapper.selectList(new QueryWrapper<Shop>());
        if(blank){
            Shop shop=new Shop();
            shop.setId(0L);
            shop.setShopName("--请选择--");
            list.add(0,shop);
        }
        return list;
    }

    public List<Shop> shoplist(Shop shop) {
        List<Shop> list = shopMapper.getlist(shop);
        return list;
    }

    @OprateLogAOP(model = "门店管理", method = "添加门店", remark = "#shop.shopName")
    public int addsave(Shop shop) {
        return shopMapper.insert(shop);
    }

    public Shop selectShopById(Long id) {
        return shopMapper.selectById(id);
    }

    @OprateLogAOP(model = "门店管理", method = "修改门店", remark = "#shop.shopName")
    public int editsave(Shop shop) {
        return shopMapper.updateById(shop);
    }

    @OprateLogAOP(model = "门店管理", method = "删除门店", remark = "#ids")
    public int remove(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        return shopMapper.deleteByIds(userIds);
    }

    @OprateLogAOP(model = "门店管理", method = "启用禁用", remark = "#id")
    public int enable(Long id, Integer status) {
        return shopMapper.enable(id,status);
    }
}
