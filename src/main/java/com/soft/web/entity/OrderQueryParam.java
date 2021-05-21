package com.soft.web.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class OrderQueryParam {
    private String key;
    private String payStatus;
    private String tradeStatus;
    private String OrderBegin;
    private String OrderEnd;
    private String PayBegin;
    private String PayEnd;
    private String SendBegin;
    private String  SendEnd;
}
