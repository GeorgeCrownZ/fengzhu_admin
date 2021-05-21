package com.soft.web.service;

import com.soft.web.dao.ProductDetailMapper;
import com.soft.web.dao.ProductPicMapper;
import com.soft.web.entity.ProductDetail;
import com.soft.web.entity.ProductPic;
import com.soft.web.util.FileUtils;
import com.soft.web.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailMapper productDetailMapper;

    public Integer addDetailPic(Long pid, String picpath) {
        Integer max = productDetailMapper.getMaxSortNumber(pid);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(pid);
        productDetail.setDetailPath(picpath);
        productDetail.setSortNumber(max+1);
        return productDetailMapper.insert(productDetail);
    }

    public List<ProductDetail> getGoodsDetail(Long pid) {
        List<ProductDetail> list = productDetailMapper.getGoodsDetail(pid);
        return list;
    }

    public ProductDetail getByid(Long picid) {
        return productDetailMapper.selectById(picid);
    }

    public Integer delGoodsDetail(Long picid) {
        return productDetailMapper.deleteById(picid);
    }

    public Boolean delFile(String detailPath) {
        String filePath = Global.getProfile() + detailPath;
        return FileUtils.deleteFile(filePath);
    }
}
