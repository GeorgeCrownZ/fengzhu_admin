package com.soft.web.util;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 *
 */
@Component
@ConfigurationProperties(prefix = "appinfo")
public class Global
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    private static String virtualpath;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    public static String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        Global.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        Global.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    public static String getVirtualpath() {
        return virtualpath;
    }

    public void setVirtualpath(String virtualpath) {
        Global.virtualpath = virtualpath;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取商品图上传路径
     */
    public static String getGoodsPic()
    {
        return getProfile() + "/goodspic";
    }

    /**
     * 获取商品详情图上传路径
     */
    public static String getGoodsDetail()
    {
        return getProfile() + "/goodsdetail";
    }

    /**
     * 获取商品品类图上传路径
     */
    public static String getCategory()
    {
        return getProfile() + "/category";
    }

    /**
     * 获取用户头像上传路径
     */
    public static String getUser()
    {
        return getProfile() + "/user";
    }

    /**
     * 获取全局设置上传路径：global
     * @return
     */
    public static String getGlobal()
    {
        return getProfile() + "/global";
    }

    /**
     * 获取轮播图上传路径：rollpic
     * @return
     */
    public static String getRollPic()
    {
        return getProfile() + "/rollpic";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取Excel导出路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }

    public static String getShopPath() {
        return getProfile() + "/shop";
    }

    public static String getCard()
    {
        return getProfile() + "/card";
    }

}
