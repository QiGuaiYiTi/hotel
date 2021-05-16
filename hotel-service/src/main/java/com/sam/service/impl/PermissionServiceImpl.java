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
}
