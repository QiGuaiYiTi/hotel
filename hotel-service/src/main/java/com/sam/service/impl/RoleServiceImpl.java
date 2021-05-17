package com.sam.service.impl;

import com.sam.dao.RoleMapper;
import com.sam.pojo.Role;
import com.sam.service.RoleService;
import com.sam.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/17
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 分页查询角色列表
     * @param roleVo
     * @return
     */
    @Override
    public List<Role> findRoleListByPage(RoleVo roleVo) {
        return roleMapper.findRoleListByPage(roleVo);
    }



    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 检查当前角色下是否存在用户信息
     * @param roleId
     * @return
     */
    @Override
    public int findUserCountByRoleId(Integer roleId) {
        return roleMapper.findUserCountByRoleId(roleId);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public int deleteRole(Integer id) {
        if(this.findUserCountByRoleId(id)>0){
            return 0;
        }else{
            return roleMapper.deleteRole(id);
        }

    }
}
