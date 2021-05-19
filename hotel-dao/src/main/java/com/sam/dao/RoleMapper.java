package com.sam.dao;

import com.sam.pojo.Role;
import com.sam.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    /**
     * 根据用户id查询用户角色列表（用于登录时校验）
     * @param userId
     * @return
     */
    List<Role> findRoleListByUserId(Integer userId);

    /**
     * 分页查询角色列表（显示角色列表）
     * @param roleVo
     * @return
     */
    List<Role> findRoleListByPage(RoleVo roleVo);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 根据角色编号查询该角色下的用户数量（主要用于删除角色前的判断）
     * @param roleId
     * @return
     */
    int findUserCountByRoleId(Integer roleId);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(Integer id);

    /**
     * 只查询角色列表
     * @return
     */
    @Select("select * from sys_role")
    List<Map<String,Object>> selectRoleListOnly();

    /**
     * 根据用户id查询该用户已经拥有的角色id列表
     * @return
     */
    @Select("select rid from sys_user_role where uid=#{sysUserId}")
    List<Integer> selectRoleIdBySysUserId(Integer sysUserId);

    /**
     * 添加角色权限关系
     * @param rid
     * @param pid
     * @return
     */
    @Insert("insert into sys_role_permission (rid,pid) values(#{rid},#{pid})")
    int insertRolePermissionList(@Param("rid") Integer rid, @Param("pid") Integer pid);

    /**
     * 删除角色权限关系
     * @param rid
     * @return
     */
    @Delete("delete from sys_role_permission where rid=#{rid}")
    void deletePermissionByRoleId(Integer rid);


}
