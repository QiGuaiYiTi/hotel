package com.sam.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Description :  后台用户（SysUser）实体类
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Data
public class SysUser {

    private Integer id;
    private String userName;
    private String password;
    private String realName;
    private Integer sex;            //性别 1男 2女
    private Integer deptId;
    private Integer status;         //状态 1可用 2禁用
    private String email;
    private String phone;
    private Integer userType;//用户类型 1超级管理员 2普通用户
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private Integer createdBy;
    private Date createDate;
    private Integer modifyBy;
    private Date modifyDate;
    private String remark;

    //用户角色集合
    private List<Role> roleList;
    //用户所属部门
    private Dept dept;

}
