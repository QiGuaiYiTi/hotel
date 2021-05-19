package com.sam.utils;

/**
 * 系统常量接口
 */
public interface SystemConstant {

    //状态码
    public static final String SUCCESS = "success";
    //验证是否存在
    public static final String EXIST = "exist";
    //文本信息
    public static final String MESSAGE = "message";
    //代表执行成功(或者数据存在)
    public static boolean TRUE = true;
    //代表执行失败(或者数据不存在)
    public static  boolean FALSE  = false;
    //Redis的key
    public static String DEPTLIST = "deptList";

    /**
     * 默认密码
     */
    public static String DEFAULT_PASSWORD = "123456";
}
