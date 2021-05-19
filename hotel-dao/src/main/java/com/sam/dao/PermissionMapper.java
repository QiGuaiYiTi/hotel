package com.sam.dao;

import com.sam.pojo.Permission;
import com.sam.vo.PermissionVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

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
    int insetPermission(Permission permission);

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    int updatePermission(Permission permission);

    /**
     * 根据父id查询菜单
     * @param id
     * @return
     */
    @Select(" select count(1) from sys_permission where pid=#{id} ")
    int selectPermissionByPId(Integer id);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    int deletePermission(Integer id);

    /**
     * 根据角色id查询该角色已经拥有的菜单id集合
     * @param roleId
     * @return
     */
    List<Integer> selectPermissionByRoleId(Integer roleId);

    /**
     * 根据当前用户id，查询当前用户的菜单权限集合，用于动态显示菜单列表
     * @param id
     * @return
     */
    List<Permission> findPermissionListByUserId(Integer id);
}
