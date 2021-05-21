package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.dao.ProductSpecMapper;
import com.soft.web.dao.SpecMapper;
import com.soft.web.entity.ProductSpec;
import com.soft.web.entity.Spec;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpecService {
    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    public List<Spec> selectSpec() {
        QueryWrapper wrapper = new QueryWrapper();
        return specMapper.selectList(wrapper);
    }

    public Object getSpecByProductId(Long id) {
        QueryWrapper wrapper = new QueryWrapper();
        List<Spec> list = specMapper.selectList(wrapper);
        List<ProductSpec> productSpeclist = productSpecMapper.getProductSpec(id);
        for (Spec spec : list) {
            for(ProductSpec productSpec : productSpeclist){
                if (spec.getId().equals(productSpec.getSpecId()))
                {
                    spec.setCk(true);
                    break;
                }
            }
        }
        return list;
    }
}
