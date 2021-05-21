package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.GoodsSayMapper;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSayService {
    @Resource
    private GoodsSayMapper goodsSayMapper;


    public List<GoodsSay> goodsSaylist(String searchkey) {
        return goodsSayMapper.goodsSaylist(searchkey);
    }

    public GoodsSay getbacksay(Long pid) {
        return goodsSayMapper.getbacksay(pid);
    }

    @OprateLogAOP(model = "商品评论",method = "设置取消精选", remark = "#id")
    public int beautify(Long id, Integer status) {
        return goodsSayMapper.beautify(id,status);
    }

    @OprateLogAOP(model = "商品评论",method = "显示隐藏", remark = "#id")
    public int showhide(Long id, Integer status) {
        return goodsSayMapper.showhide(id,status);
    }

    @OprateLogAOP(model = "商品评论",method = "回复用户评论", remark = "#id")
    public int replysaysave(Long id, String content) {

        GoodsSay goodsSay = new GoodsSay();
        goodsSay.setPid(id);
        goodsSay.setSayContent(content);
        return goodsSayMapper.insert(goodsSay);
    }

    public List<GoodsSay> getbacksayList(Long id) {
        List<GoodsSay> list = goodsSayMapper.getbacksayList(id);
        return list;
    }
}
