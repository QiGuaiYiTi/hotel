package com.sam.service.impl;

import com.sam.dao.PermissionMapper;
import com.sam.pojo.Permission;
import com.sam.service.PermissionService;
import com.sam.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询权限列表（对应加载首页菜单功能）
     * @param permissionVo
     * @return
     */
    @Override
    public List<Permission> findPermissionList(PermissionVo permissionVo) {
        return permissionMapper.findPermissionList(permissionVo);
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @Override
    public int addPermission(Permission permission) {
        //判断是否为一级菜单
        if(permission.getPid()==null){
            permission.setPid(0);
        }
        //设置菜单打开方式
        permission.setTarget("_self");
        return permissionMapper.insetPermission(permission);
    }

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    @Override
    public int modifyPermission(Permission permission) {
        return permissionMapper.updatePermission(permission);
    }

    /**
     * 根据父id查询子菜单
     * @param id
     * @return
     */
    @Override
    public int findPermissionByPId(Integer id) {
        return permissionMapper.selectPermissionByPId(id);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public int removePermission(Integer id) {
        return permissionMapper.deletePermission(id);
    }

    /**
     * 根据角色id查询当前角色所拥有的权限编号集合
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findPermissionByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionByRoleId(roleId);
    }

    /**
     * 根据当前用户id，查询当前用户的菜单权限集合，用于动态显示菜单列表
     * @param id
     * @return
     */
    @Override
    public List<Permission> findPermissionListByUserId(Integer id) {
        return permissionMapper.findPermissionListByUserId(id);
    }
}


