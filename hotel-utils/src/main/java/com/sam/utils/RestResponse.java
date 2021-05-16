package com.sam.utils;

import java.util.HashMap;

/**
 * Description : Rest 接口返回数据
 *
 * @author : Sam
 * @created : 2021/5/16
 */

public class RestResponse extends HashMap<String, Object>{

    /**
     * 禁止通过构造方法构造对象，只能通过静态方法获取实例
     */
    private RestResponse(){

    }

    /**
     * 设置接口返回的数据项，并指定数据项的属性
     * @param key
     * @param value
     * @return
     */
    public RestResponse put(String key,Object value){
        super.put(key,value);
        return this;
    }

    /**
     * 设置接口返回的文本信息
     * @param msg
     * @return
     */
    public RestResponse msg(String msg){
        this.put(SystemConstant.MESSAGE,msg);
        return this;
    }

    /**
     * 接口执行成功的返回数据，并携带文本信息
     * @param msg
     * @return
     */
    public static RestResponse ok(String msg){
        RestResponse result = new RestResponse();
        result.put(SystemConstant.SUCCESS,SystemConstant.TRUE).msg(msg);
        return result;
    }

    /**
     * 接口执行失败的返回数据，并携带文本信息
     * @param msg
     * @return
     */
    public static RestResponse fail(String msg){
        RestResponse result = new RestResponse();
        result.put(SystemConstant.SUCCESS,SystemConstant.FALSE).msg(msg);
        return result;
    }

    /**
     * 数据已存在的返回数据，并携带文本信息
     * @param msg
     * @return
     */
    public static RestResponse exist(String msg){
        RestResponse result = new RestResponse();
        result.put(SystemConstant.EXIST,SystemConstant.TRUE).msg(msg);
        return result;
    }
    /**
     * 数据不存在的返回数据，并携带文本信息
     * @param msg
     * @return
     */
    public static RestResponse notExist(String msg){
        RestResponse result = new RestResponse();
        result.put(SystemConstant.EXIST,SystemConstant.FALSE).msg(msg);
        return result;
    }
}


