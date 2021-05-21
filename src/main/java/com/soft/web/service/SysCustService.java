package com.soft.web.service;

import com.soft.web.dao.SysCustMapper;
import com.soft.web.entity.SysCust;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCustService {
    @Autowired
    private SysCustMapper sysCustMapper;

    public List<SysCust> selectCustList(SysCust cust) {
        System.out.println("-------------查询数据库-----------------");
        return sysCustMapper.selectCustList(cust);
    }

    public int insertCust(SysCust cust) {
        int r = sysCustMapper.insert(cust);
        return r;
    }

    public SysCust selectCustById(Long cid) {
        SysCust cust = sysCustMapper.selectById(cid);
        return cust;
    }

    public int updateCust(SysCust cust) {
        int r = sysCustMapper.updateById(cust);
        return r;
    }

    public int deleteCustByIds(String ids) {
        Long[] custIds = Convert.toLongArray(ids);
        return sysCustMapper.deleteCustByIds(custIds);
    }
}
