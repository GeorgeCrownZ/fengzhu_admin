package com.soft.web.service;

import com.soft.web.dao.ProductMapper;
import com.soft.web.dao.ProductPicMapper;
import com.soft.web.entity.ProductPic;
import com.soft.web.util.FileUtils;
import com.soft.web.util.Global;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPicService {
    @Autowired
    private ProductPicMapper productPicMapper;

    public Integer addShowPic(Long pid, String picpath) {
        Integer max = productPicMapper.getMaxSortNumber(pid);
        ProductPic productPic = new ProductPic();
        productPic.setProductId(pid);
        productPic.setPicPath(picpath);
        productPic.setSortNumber(max+1);
        return productPicMapper.insert(productPic);
    }

    public List<ProductPic> getGoodsPic(Long pid) {
        List<ProductPic> list = productPicMapper.getGoodsPic(pid);
        return list;
    }

    public ProductPic getByid(Long picid) {
        return productPicMapper.selectById(picid);
    }

    public Integer delGoodsPic(Long picid) {
        return productPicMapper.deleteById(picid);
    }

    public Boolean delFile(String picPath) {
        String filePath = Global.getProfile() + picPath;
        return FileUtils.deleteFile(filePath);
    }
}
