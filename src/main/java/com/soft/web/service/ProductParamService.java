package com.soft.web.service;

import com.soft.web.common.AjaxResult;
import com.soft.web.dao.ProductDetailMapper;
import com.soft.web.dao.ProductParamMapper;
import com.soft.web.entity.ProductParam;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductParamService {
    @Autowired
    private ProductParamMapper productParamMapper;


    public List<ProductParam> productParamList(Long pid) {
        return productParamMapper.productParamList(pid);
    }

    public int addparam(ProductParam productParam) {
        return productParamMapper.insert(productParam);
    }

    public int delparam(String ids) {
        Long[] paramids = Convert.toLongArray(ids);
        return productParamMapper.deleteInBatch(paramids);
    }
}
