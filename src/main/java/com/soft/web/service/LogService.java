package com.soft.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.web.dao.LogMapper;
import com.soft.web.entity.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogService {
    @Resource
    private LogMapper logMapper;

    public int insert(Log log) {
        return logMapper.insert(log);
    }

    public List<Log> loglist(String searchkey) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("opname",searchkey);
        wrapper.or();
        wrapper.like("modelname",searchkey);
        wrapper.or();
        wrapper.like("methodname",searchkey);
        wrapper.or();
        wrapper.like("remark",searchkey);
        wrapper.or();
        wrapper.like("ip",searchkey);
        return logMapper.selectList(wrapper);
    }
}
