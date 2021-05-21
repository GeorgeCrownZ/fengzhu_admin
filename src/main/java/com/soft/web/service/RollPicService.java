package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.RollPicMapper;
import com.soft.web.entity.RollPic;
import com.soft.web.entity.Spec;
import com.soft.web.util.Convert;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RollPicService {

    @Autowired
    RollPicMapper rollPicMapper;

    public List<RollPic> selectAllRollPic() {
        return rollPicMapper.selectAllRollPic();
    }

    @OprateLogAOP(model = "轮播图管理", method = "添加轮播图", remark = "#rollPic.id")
    public int addRollPic(RollPic rollPic) {
        return rollPicMapper.insert(rollPic);
    }

    @OprateLogAOP(model = "轮播图管理", method = "修改轮播图", remark = "#rollPic.id")
    public int updateRollPicById(RollPic rollPic) {
        if(rollPic.getPicPath()==null) {
            RollPic rollPic1 = selectRollPicById(rollPic.getId());
            System.out.println(rollPic);
            System.out.println(rollPic1);
            rollPic.setPicPath(rollPic1.getPicPath());
        }
        return rollPicMapper.updateById(rollPic);
    }

    @OprateLogAOP(model = "轮播图管理", method = "删除轮播图", remark = "#ids")
    public int deleteRollPics(String ids) {
        Long[] sids = Convert.toLongArray(ids);
        List<Long> rollPics = new ArrayList(Arrays.asList(sids));
        int result = rollPicMapper.deleteRolls(rollPics);
        if(result>0) {
            return result;
        }
        return -1;
    }

    @OprateLogAOP(model = "轮播图管理", method = "是否启用", remark = "#rollPic.id")
    public int updateStatus(RollPic rollPic) {
        return rollPicMapper.updateStatus(rollPic);
    }

    public RollPic selectRollPicById(Long id) {
        return rollPicMapper.selectById(id);
    }
}
