package com.soft.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberSayMapper extends BaseMapper<Member> {

    public List<GoodsSay> membersays(@Param("userId")Long userId);

    public GoodsSay backmembersays(@Param("pid")Long pid);

}
