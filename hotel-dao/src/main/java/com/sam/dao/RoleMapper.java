package com.sam.dao;

import com.sam.pojo.Role;

import java.util.List;

public interface RoleMapper {

    /**
     * 根据用户id查询用户角色列表（用于登录时校验）
     * @param userId
     * @return
     */
    List<Role> findRoleListByUserId(Integer userId);

}
