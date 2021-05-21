package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.entity.Global;
import com.soft.web.service.GlobalService;
import com.soft.web.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("global")
public class GlobalController {

    @Autowired
    GlobalService globalService;

    @GetMapping("/allsettings")
    public ModelAndView allsettings() {
        ModelAndView modelAndView = new ModelAndView();
        Global global = globalService.selectGlobalById(1L);
        if(global==null) {
            global = new Global();
            global.setWebLogo("/img/uppic.jpg");
            global.setNoticePic1("/img/uppic.jpg");
        }
        modelAndView.addObject("global",global);
        modelAndView.setViewName("global/allsettings");
        return modelAndView;
    }

    @PostMapping("/addglobal")
    public AjaxResult addglobal(Global global, @RequestParam("logo") MultipartFile logo, @RequestParam("nopic") MultipartFile[] nopic,@RequestParam("minepic") MultipartFile[] minepic) {
        try {
            if (!logo.isEmpty()) {
                String webLogoPath = FileUploadUtils.upload(com.soft.web.util.Global.getGlobal(), logo);
                global.setWebLogo(webLogoPath);
            }
            for(int i=0;i<nopic.length;i++) {
                if(!nopic[i].isEmpty()){
                    String pic = FileUploadUtils.upload(com.soft.web.util.Global.getGlobal(), nopic[i]);
                    if(i == 0) {
                        global.setNoticePic1(pic);
                    } else if(i == 1) {
                        global.setNoticePic2(pic);
                    }
                    else if(i == 2) {
                        global.setNoticePic3(pic);
                    } else if(i == 3) {
                        global.setNoticePic4(pic);
                    }
                }
            }
            for(int i=0;i<minepic.length;i++) {
                if(!minepic[i].isEmpty()){
                    String mpic = FileUploadUtils.upload(com.soft.web.util.Global.getGlobal(), minepic[i]);
                    if(i == 0) {
                        global.setMinePic1(mpic);
                    } else if(i == 1) {
                        global.setMinePic2(mpic);
                    }
                }
            }
            int result = 0;
            if(global.getId() == null) {
                result = globalService.addGlobal(global);
            } else {
                result = globalService.updateGlobal(global);
            }

            if(result <=0) {
                return AjaxResult.toAjax(-1);
            }
            return AjaxResult.toAjax(result);

        } catch (Exception e) {
            AjaxResult.error(e.getMessage());
        }
        return AjaxResult.toAjax(-1);
    }
}
