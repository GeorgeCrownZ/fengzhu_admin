package com.soft.web.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
public class SysConfigConstant {

//    @Value("${web.upload-path}")
//    private String uploadpath;

    @Value("${web.virtual-path}")
    private String virtualpath;


}
