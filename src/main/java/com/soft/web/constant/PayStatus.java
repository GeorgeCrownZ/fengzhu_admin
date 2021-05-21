package com.soft.web.constant;

import com.soft.web.vo.KeyValueVO;
import kotlin.collections.ArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class PayStatus {
    /**
     * 未付款
     */
    public static final Integer NoPay=0;//未付款
    /**
     * 已付款
     */
    public static final Integer Paid=1;//已付款

    public static String toString(Integer payStatus) {
        if(payStatus==null) payStatus=0;
        if(payStatus==0){
            return "未付款";
        }
        else if(payStatus==1){
            return "已付款";
        }
        else
        {
            return "";
        }
    }

    public static List<KeyValueVO> getList(boolean blank) {
        List<KeyValueVO> list = new ArrayList<>();
        if(blank){
            list.add(new KeyValueVO("","--请选择--"));
        }
        list.add(new KeyValueVO("0","未付款"));
        list.add(new KeyValueVO("1","已付款"));
        return list;
    }
}
