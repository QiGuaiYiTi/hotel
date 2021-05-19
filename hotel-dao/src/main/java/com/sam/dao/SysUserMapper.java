package com.sam.dao;

import com.sam.pojo.SysUser;
import com.sam.vo.SysUserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据用户名查询用户信息，对应登录功能
     * @param userName
     * @return
     */
    SysUser findSysUserByUserName(String userName);

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
    int insetSysUser(SysUser sysUser);

    /**
     * 修改员工
     * @param sysUser
     * @return
     */
    int updateSysUserById(SysUser sysUser);

    /**
     * 删除用户角色关系
     * @param id
     * @return
     */
    int deleteSysUserRoleById(Integer id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteSysUserById(Integer id);


    /**
     * 给用户分配角色
     * @param sysUserId
     * @param roleId
     * @return
     */
    @Insert(" insert into sys_user_role (uid,rid) values(#{sysUserId},#{roleId})")
    void saveSysUserRole(@Param("sysUserId") Integer sysUserId, @Param("roleId") Integer roleId);
}
