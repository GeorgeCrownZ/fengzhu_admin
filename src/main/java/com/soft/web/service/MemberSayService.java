package com.soft.web.service;

import com.soft.web.dao.MemberSayMapper;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberSayService {

    @Autowired
    MemberSayMapper memberSayMapper;

    public List<GoodsSay> membersays(Long userId) {
        List<GoodsSay> membersays = memberSayMapper.membersays(userId);
        return membersays;
    }

    public GoodsSay backmembersays(@Param("pid")Long pid) {
        GoodsSay goodsSay = memberSayMapper.backmembersays(pid);
        return goodsSay;
    }

}
