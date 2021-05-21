package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.ExpressMapper;
import com.soft.web.entity.Express;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author md
 * @since 2021-03-22
 */
@Service("express")
public class ExpressService {

    @Autowired
    ExpressMapper expressMapper;

    public List<Express> selectAllExpress(String searchkey) {
        return expressMapper.selectAllExpress(searchkey);
    }

    @OprateLogAOP(model = "快递管理", method = "添加快递信息", remark = "#express.expressName")
    public int addExpress(Express express) {
        int result = expressMapper.insert(express);
        if(result>0) {
            return result;
        }
        return -1;
    }

    @OprateLogAOP(model = "快递管理", method = "添加快递信息", remark = "#ids")
    public int deleteByIds(String ids) {
        Long[] sids = Convert.toLongArray(ids);
        List<Long> expressList = (Arrays.asList(sids));
        int result = expressMapper.deleteSpecByIds(expressList);
        if(result>0) {
            return result;
        }
        return -1;
    }

    public Express selectExpressById(Long id) {
        return expressMapper.selectById(id);
    }

    @OprateLogAOP(model = "快递管理", method = "修改快递信息", remark = "#express.expressName")
    public int updateExpress(Express express) {
        int result = expressMapper.updateById(express);
        if(result>0) {
            return result;
        }
        return -1;
    }
}
