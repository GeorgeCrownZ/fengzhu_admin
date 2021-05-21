package com.soft.web.constant;

public class SendStatus {
    /**
     * 未发货
     */
    public static final Integer NoSend=0;//未发货

    /**
     * 已发货
     */
    public static final Integer Sent=1;//已发货

    public static String toString(Integer sendStatus) {
        if(sendStatus==null) sendStatus=0;
        if(sendStatus==0){
            return "未发货";
        }
        else if(sendStatus==1){
            return "已发货";
        }
        else
        {
            return "";
        }
    }
}
