package com.sam.service.impl;

import com.sam.dao.RoleMapper;
import com.sam.dao.SysUserMapper;
import com.sam.pojo.Role;
import com.sam.pojo.SysUser;
import com.sam.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 用户登录功能，（对应后台用户登录功能）
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //定义集合用于存储用户角色列表
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //调用查询方法，获取用户名，密码,角色列表
        SysUser sysUser = sysUserMapper.findSysUserByUserName(userName);
        //遍历集合
        for (Role role : sysUser.getRoleList()) {
            //将角色放入之前定义的用于存储用户角色列表的集合
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        //创建User对象(主要是封装用户名，密码，角色集合，用户状态是否可用等)
        User user = new User(sysUser.getUserName(),
                                sysUser.getPassword(),
                                sysUser.getStatus()==1,
                                true,
                                true,
                                true,
                                authorities);
        return user;
    }

    /**
     * 根据用户名查询用户，用户登录时用户名输入框失去焦点时检查用户名是否存在
     * @param userName
     * @return
     */
    @Override
    public SysUser findSysUserByUserName(String userName) {
        return sysUserMapper.findSysUserByUserName(userName);
    }

    /**
     * 根据部门编号查询员工信息（用于删除部门前的校验）
     * @param deptId
     * @return
     */
    @Override
    public List<SysUser> selectSysUserByDeptId(Integer deptId) {
        return sysUserMapper.selectSysUserByDeptId(deptId);
    }
}
