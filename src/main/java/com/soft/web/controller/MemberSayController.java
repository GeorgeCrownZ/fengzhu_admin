package com.soft.web.controller;

import com.soft.web.common.MyListUtils;
import com.soft.web.entity.GoodsSay;
import com.soft.web.entity.Member;
import com.soft.web.service.MemberSayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/membersay")
public class MemberSayController {

    @Autowired
    MemberSayService memberSayService;

    @PostMapping("/membersay/{id}")
    public Object membersay(@PathVariable("id")Long id) {
        List<GoodsSay> membersays = memberSayService.membersays(id);
        return MyListUtils.getDataTable(membersays);
    }

    @PostMapping("/getbacksay/{id}")
    public GoodsSay getbacksay(@PathVariable("id")Long id) {
        return memberSayService.backmembersays(id);
    }
}
