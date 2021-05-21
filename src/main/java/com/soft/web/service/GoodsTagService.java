package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.GoodsTagMapper;
import com.soft.web.entity.Spec;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GoodsTagService {

    @Autowired
    GoodsTagMapper goodsTagMapper;

    public List<Spec> selectSpecList(){
        List<Spec> specList = goodsTagMapper.selectSpecList();
        for (Spec spec : specList) {
            int count = goodsTagMapper.selectCountBySpecId(spec.getId()).size();
            spec.setCount(count);
        }
        return specList;
    }

    @OprateLogAOP(model = "商品标签",method = "修改商品标签", remark = "#spec.specName")
    public int addSpec(Spec spec){
        spec.setStatus(0);
        spec.setAddTime(new Date());
        spec.setUpdateTime(new Date());
        return goodsTagMapper.addSpec(spec);
    }

    @OprateLogAOP(model = "商品标签",method = "修改商品标签", remark = "#spec.id")
    public int updateSpec(Spec spec){
        spec.setUpdateTime(new Date());
        return goodsTagMapper.updateSpec(spec);
    }

    @OprateLogAOP(model = "商品标签",method = "删除商品标签", remark = "#ids")
    public int deleteSpecByIds(String ids) {
        Long[] sids = Convert.toLongArray(ids);
        List<Long> specList = (Arrays.asList(sids));
        goodsTagMapper.deleteSpecByIds(specList);
        return 1;
    }

    public Spec selectSpecById(Long id) {
        return goodsTagMapper.selectSpecById(id);
    }

    @OprateLogAOP(model = "商品标签",method = "修改状态", remark = "#spec.id")
    public int updateStatus(Spec spec) {
        spec.setUpdateTime(new Date());
        return goodsTagMapper.updateStatus(spec);
    }

    public List selectCountBySpecId(Long id){
        return goodsTagMapper.selectCountBySpecId(id);
    }
}
