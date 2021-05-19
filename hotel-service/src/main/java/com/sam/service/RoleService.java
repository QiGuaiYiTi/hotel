package com.sam.service;


import com.sam.pojo.Role;
import com.sam.vo.RoleVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 只查询角色列表
     * @return
     */
    List<Map<String,Object>> selectRoleListOnly();

    /**
     * 根据用户id查询该用户已经拥有的角色id列表
     * @return
     */
    List<Integer> selectRoleIdBySysUserId(Integer sysUserId);

    /**
     * 添加角色权限关系
     * @param rid
     * @param pid
     * @return
     */
    public boolean saveRolePermission(Integer rid,String pid);


}
