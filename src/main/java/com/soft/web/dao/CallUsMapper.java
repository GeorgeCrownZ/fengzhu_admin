package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.CallUs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author md
 * @since 2021-03-19
 */
@Mapper
@Repository
public interface CallUsMapper extends BaseMapper<CallUs> {

    public List<CallUs> selectAllList(String searchkey);

    public List<CallUs> selectAllListByPid(Long id);

    public int addReplySay(CallUs callUs);

    public CallUs selectByUserId(Long id);

}
