package com.soft.web.common;

import com.github.pagehelper.PageInfo;
import com.soft.web.util.TableDataInfo;

import java.util.List;

/******************
 * List工具类
 *
 ******************/
public class MyListUtils {

    /*****************************
     * 验证List不为空
     * 
     * @param list
     * @return 不为空返回true,为空返回false
     *****************************/
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(List list) {
        if (null != list && 0 < list.size()) {
            return true;
        } else {
            return false;
        }
    }

    public static TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

}
