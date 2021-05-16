package com.sam.dao;

import com.sam.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据用户名查询用户信息，对应登录功能
     * @param userName
     * @return
     */
    SysUser findSysUserByUserName(String userName);

    /**
     * 根据部门编号查询员工信息（用于删除部门前的校验）
     * @param deptId
     * @return
     */
    List<SysUser> selectSysUserByDeptId(Integer deptId);
}
