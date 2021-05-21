package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.ProductSpec;
import com.soft.web.entity.RollPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RollPicMapper extends BaseMapper<RollPic> {

    public List<RollPic> selectAllRollPic();

    public int updateStatus(RollPic rollPic);

    public int deleteRolls(@Param("rollPics") List<Long> rollPics);
}
