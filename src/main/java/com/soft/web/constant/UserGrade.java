package com.soft.web.constant;

import com.soft.web.vo.KeyValueVO;

import java.util.ArrayList;
import java.util.List;

public class UserGrade {
    /**
     * 游客
     */
    public static final Integer visitor=0;

    /**
     * 专员
     */
    public static final Integer worker=1;

    /**
     * 主任
     */
    public static final Integer header=2;

    /**
     * 经理
     */
    public static final Integer manager=3;


    /**
     * 总监
     */
    public static final Integer director=4;

    /**
     * 总裁
     */
    public static final Integer master=5;

    public static String toString(Integer status) {
        if(status==null) status=0;
        if(status==0){
            return "游客";
        }
        else if(status==1){
            return "专员";
        }
        else if(status==2){
            return "主任";
        }
        else if(status==3){
            return "经理";
        }
        else if(status==4){
            return "总监";
        }
        else if(status==5){
            return "总裁";
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
        list.add(new KeyValueVO("0","游客"));
        list.add(new KeyValueVO("1","专员"));
        list.add(new KeyValueVO("2","主任"));
        list.add(new KeyValueVO("3","经理"));
        list.add(new KeyValueVO("4","总监"));
        list.add(new KeyValueVO("5","总裁"));
        return list;
    }

}
