package com.sam.service;

import com.sam.pojo.SysUser;
import com.sam.vo.SysUserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {

    /**
     * 根据用户名查询用户，用户登录时用户名输入框失去焦点时检查用户名是否存在
     * @param userName
     * @return
     */
    SysUser findSysUserByUserName(String userName);

    /**
     * 重置密码
     * @param id
     * @return
     */
    public int resetPwd(Integer id);

    /**
     * 根据部门编号查询员工信息（用于删除部门前的校验）
     * @param deptId
     * @return
     */
    List<SysUser> selectSysUserByDeptId(Integer deptId);

    /**
     * 分页查询用户信息，包括部门名称（需要连表）
     * @param sysUserVo
     * @return
     */
    List<SysUser> selectSysUserListByPage(SysUserVo sysUserVo);

    /**
     * 添加员工
     * @param sysUser
     * @return
     */
    int addSysUser(SysUser sysUser);

    /**
     * 修改员工
     * @param sysUser
     * @return
     */
    public int modifySysUser(SysUser sysUser);

    /**
     * 删除员工
     * @param id
     * @return
     */
    public int removeSysUser(Integer id);

    /**
     * 给用户分配角色
     * @param sysUserId
     * @param ids
     * @return
     */
    boolean saveSysUserRole(String ids, Integer sysUserId);
}
