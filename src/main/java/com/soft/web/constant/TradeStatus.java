package com.soft.web.constant;

import com.soft.web.vo.KeyValueVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易状态常量
 */
public class TradeStatus {
    /**
     * 已取消
     */
    public static final Integer Cancel=1;//已取消
    /**
     * 待发货
     */
    public static final Integer Sending=2;//待发货
    /**
     * 待收货
     */
    public static final Integer Receiving=3;//待收货
    /**
     * 已签收
     */
    public static final Integer Received=4;//已签收

    public static String toString(Integer tradeStatus) {
        if(tradeStatus==null) tradeStatus=0;
        if(tradeStatus==1){
            return "已取消";
        }
        else if(tradeStatus==2){
            return "待发货";
        }
        else if(tradeStatus==3){
            return "待收货";
        }
        else if(tradeStatus==4){
            return "已签收";
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
        list.add(new KeyValueVO("1","已取消"));
        list.add(new KeyValueVO("2","待发货"));
        list.add(new KeyValueVO("3","待收货"));
        list.add(new KeyValueVO("4","已签收"));
        return list;
    }

}
