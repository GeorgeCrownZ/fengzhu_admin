package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    List<Member> memberlist(String searchkey);

    int enable(Long id, Integer status);


    int deleteInBatch(Long[] user_ids);

    int setEmployee(Long id, Integer status);

    List<UserAddress> selectAddressByUserId(@Param("userId")Long userId);
}
