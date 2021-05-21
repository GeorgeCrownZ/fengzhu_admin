package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.CallUsMapper;
import com.soft.web.entity.CallUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author md
 * @since 2021-03-19
 */
@Service
public class CallUsService {

    @Autowired
    CallUsMapper callUsMapper;

    public List<CallUs> selectAllList(String searchkey) {
        return callUsMapper.selectAllList(searchkey);
    }

    public List<CallUs> selectAllListByPid(Long id) {
        return callUsMapper.selectAllListByPid(id);
    }

    @OprateLogAOP(model = "联系我们管理", method = "回复", remark = "#callUs.saycontent")
    public int insertCall(CallUs callUs) {
        List<CallUs> callUses = selectAllListByPid(callUs.getPid());

        if(callUses.size()==0) {
            int result = callUsMapper.insert(callUs);
            if(result>0) {
                return result;
            }
            return -1;
        }
        return -1;
    }

    public CallUs selectByUserId(Long id) {
        return callUsMapper.selectByUserId(id);
    }
}
