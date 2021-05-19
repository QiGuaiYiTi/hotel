package com.sam.service;

import com.sam.pojo.Permission;
import com.sam.vo.PermissionVo;

import java.util.List;

public interface PermissionService {

    /**
     * 查询权限列表（对应加载首页菜单功能）
     * @param permissionVo
     * @return
     */
    List<Permission> findPermissionList(PermissionVo permissionVo);

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    int addPermission(Permission permission);

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    int modifyPermission(Permission permission);

    /**
     * 根据父id查询子菜单
     * @param id
     * @return
     */
    int findPermissionByPId(Integer id);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    int removePermission(Integer id);

    /**
     * 根据角色id查询当前角色所拥有的权限编号集合
     * @param roleId
     * @return
     */
    List<Integer> findPermissionByRoleId(Integer roleId);

    /**
     * 根据当前用户id，查询当前用户的菜单权限集合
     * @param id
     * @return
     */
    List<Permission> findPermissionListByUserId(Integer id);
}
