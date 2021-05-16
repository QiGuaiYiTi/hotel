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


}
