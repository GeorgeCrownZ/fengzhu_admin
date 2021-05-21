package com.soft.web.dao;

import com.soft.web.entity.Express;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author md
 * @since 2021-03-22
 */
@Repository
@Mapper
public interface ExpressMapper extends BaseMapper<Express> {

    public int deleteSpecByIds(@Param("expressList") List<Long> expressList);

    public List<Express> selectAllExpress(String searchkey);

}
