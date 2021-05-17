package com.sam.service;


import com.sam.pojo.Role;
import com.sam.vo.RoleVo;

import java.util.List;

public interface RoleService {

    /**
     * 分页查询角色列表（显示角色列表）
     * @param roleVo
     * @return
     */
    List<Role> findRoleListByPage(RoleVo roleVo);


    /**
     * 添加角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(Integer id);

    /**
     * 检查当前角色下是否存在用户信息
     * @param roleId
     * @return
     */
    int findUserCountByRoleId(Integer roleId);


}
