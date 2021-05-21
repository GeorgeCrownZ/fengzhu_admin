package com.soft.web.constant;
/**
 * 订单状态常量
 */
public class OrderStatus {
    /**
     * 1 生效中
     */
    public static final Integer Enable=1;

    /**
     *  2 已删除
     */
    public static final Integer Deleted=2;

    /**
     *  999 超时订单
     */
    public static final Integer OverTime=999;

    public static String toString(Integer orderStatus) {
        if(orderStatus==null) orderStatus=0;
        if(orderStatus==1){
            return "生效中";
        }
        else if(orderStatus==2){
            return "已删除";
        }
        else if(orderStatus==999){
            return "超时订单";
        }
        else
        {
            return "";
        }
    }
}
