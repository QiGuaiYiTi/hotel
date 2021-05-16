package com.sam.service;

import com.sam.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {

    /**
     * 根据用户名查询用户，用户登录时用户名输入框失去焦点时检查用户名是否存在
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
