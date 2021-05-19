package com.sam.pojo;

import lombok.Data;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@Data
public class Permission {

    private Integer id;                 //菜单编号
    private Integer pid;                //父级菜单编号
    private String type;                //类型，menu为菜单，permission为权限
    private String permissionCode;      //权限编号
    private String icon;                //菜单图标
    private String href;                //菜单地址
    private Integer spread;             //是否展开 1为展开 2为折叠
    private String title;               //菜单名称
    private String target;              //打开方式


}
