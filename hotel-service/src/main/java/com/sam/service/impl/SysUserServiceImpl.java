package com.sam.service.impl;

import com.sam.dao.RoleMapper;
import com.sam.dao.SysUserMapper;
import com.sam.pojo.Role;
import com.sam.pojo.SysUser;
import com.sam.service.SysUserService;
import com.sam.utils.PasswordUtil;
import com.sam.utils.SystemConstant;
import com.sam.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名不存在或者密码错误");
        }
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
     * 重置密码
     * @param id
     * @return
     */
    @Override
    public int resetPwd(Integer id) {
        //创建一个用户对象
        SysUser sysUser = new SysUser();
        //设置主键
        sysUser.setId(id);
        //设置重置后的密码
        sysUser.setPassword(PasswordUtil.encode(SystemConstant.DEFAULT_PASSWORD));
        //设置修改时间
        sysUser.setModifyDate(new Date());
        return sysUserMapper.updateSysUserById(sysUser);
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

    /**
     * 分页查询用户信息，包括部门名称（需要连表）
     * @param sysUserVo
     * @return
     */
    @Override
    public List<SysUser> selectSysUserListByPage(SysUserVo sysUserVo) {
        return sysUserMapper.selectSysUserListByPage(sysUserVo);
    }

    /**
     * 添加员工
     * @param sysUser
     * @return
     */
    @Override
    public int addSysUser(SysUser sysUser) {
        //设置用户相关信息
        //设置创建时间
        sysUser.setCreateDate(new Date());
        //设置用户类型
        sysUser.setUserType(2);//默认为普通用户
        //使用默认密码，并加密处理
        sysUser.setPassword(PasswordUtil.encode(SystemConstant.DEFAULT_PASSWORD));
        return sysUserMapper.insetSysUser(sysUser);
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @Override
    public int modifySysUser(SysUser sysUser) {
        //设置修改时间
        sysUser.setModifyDate(new Date());
        return sysUserMapper.updateSysUserById(sysUser);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int removeSysUser(Integer id) {
        //先删除用户角色关系
        sysUserMapper.deleteSysUserRoleById(id);
        //再删除用户
        return sysUserMapper.deleteSysUserById(id);
    }

    /**
     * 给用户分配角色
     * @param sysUserId
     * @param ids
     * @return
     */
    public boolean saveSysUserRole(String ids, Integer sysUserId) {

        //分配角色前清空原有角色
        sysUserMapper.deleteSysUserRoleById(sysUserId);

        try {
            //将字符串转换为数组
            String[] roleId = ids.split(",");
            //循环遍历，为用户添加角色
            for (int i = 0; i <roleId.length ; i++) {
                sysUserMapper.saveSysUserRole(sysUserId,Integer.valueOf(roleId[i]));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
