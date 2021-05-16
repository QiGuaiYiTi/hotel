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

    private Integer id;
    private Integer pid;
    private String type;
    private String permissionCode;
    private String icon;
    private String href;
    private Integer spread;
    private String title;
    private String target;


}
