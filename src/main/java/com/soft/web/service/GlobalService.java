package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.GlobalMapper;
import com.soft.web.entity.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalService {

    @Autowired
    GlobalMapper globalMapper;

    @OprateLogAOP(model = "系统",method = "修改全局设置")
    public int updateGlobal(Global global){
        Global global1 = globalMapper.selectById(1L);
        if(global.getWebLogo()==null) {
            global.setWebLogo(global1.getWebLogo());
        }
        if(global.getNoticePic1()==null) {
            global.setNoticePic1(global1.getNoticePic1());
            global.setNoticePic2(global1.getNoticePic2());
            global.setNoticePic3(global1.getNoticePic3());
            global.setNoticePic4(global1.getNoticePic4());
        } else {
            if(global.getNoticePic2()==null){
                global.setNoticePic2("/img/uppic.jpg");
            }
            if(global.getNoticePic3()==null){
                global.setNoticePic3("/img/uppic.jpg");
            }
            if(global.getNoticePic4()==null){
                global.setNoticePic4("/img/uppic.jpg");
            }
        }
        return globalMapper.updateById(global);
    }

    public Global selectGlobalById(Long id) {
        return globalMapper.selectById(id);
    }

    public int addGlobal(Global global) {
        global.setId(1L);
        System.out.println(global);
        return globalMapper.insert(global);
    }

}
