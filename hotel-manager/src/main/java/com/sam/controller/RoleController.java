package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.pojo.Permission;
import com.sam.pojo.Role;
import com.sam.service.PermissionService;
import com.sam.service.RoleService;
import com.sam.utils.DataGridViewResult;
import com.sam.utils.RestResponse;
import com.sam.utils.TreeNode;
import com.sam.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/17
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {



    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 分页查询角色列表（显示角色列表）
     * @param roleVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo){
        //设置分页信息
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        //查询角色列表
        List<Role> roleList = roleService.findRoleListByPage(roleVo);
        //创建分页对象
        PageInfo<Role> rolePageInfo = new PageInfo<Role>(roleList);
        //返回数据
        return new DataGridViewResult(rolePageInfo.getTotal(),rolePageInfo.getList());
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public String addRole(Role role){
        if(roleService.insertRole(role)>0){
            RestResponse ok = RestResponse.ok("角色添加成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("角色添加失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    public String updateRole(Role role){
        if(roleService.updateRole(role)>0){
            RestResponse ok = RestResponse.ok("角色修改成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("角色修改失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     *  根据角色编号查询用户信息，（主要用于删除角色前的判断）
     * @param roleId
     * @return
     */
    @RequestMapping("/checkRoleHasUser")
    public String checkRoleHasUser(Integer roleId){
        if(roleService.findUserCountByRoleId(roleId)>0){
            RestResponse exist = RestResponse.exist("该角色正在被使用，无法删除...");
            return  JSON.toJSONString(exist);
        }else{
            RestResponse notExist = RestResponse.notExist("");
            return JSON.toJSONString(notExist);
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        if(roleService.deleteRole(id)>0){
            RestResponse ok = RestResponse.ok("角色删除成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("角色删除失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 初始化权限列表树（角色管理模块，点击分配菜单按钮）
     * @return
     */
    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId){
        // 查询所有菜单
        List<Permission> permissions = permissionService.findPermissionList(null);
        // 根据当前角色id，查询当前角色已经拥有菜单限编号集合
        List<Integer> permissionIds = permissionService.findPermissionByRoleId(roleId);
        //创建集合保存节点信息
        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();

        //循环遍历菜单列表集合
        for (Permission permission : permissions) {
            // 获取当前菜单的id
            Integer permissionId = permission.getId();
            //定义变量标识是否选中
            String checkArr = "0"; //默认为0 ，标识不选中
            //内存循环遍历当前角色已经拥有的菜单编号集合
            if(permissionIds!=null || permissionIds.size()>0){
                for (Integer id : permissionIds) {
                    //如果 当前菜单id 和已拥有菜单id相等，则修改布尔变量的值
                    if(permissionId==id){
                        checkArr="1";   //标识选中
                        break;
                    }
                }
            }

            //判断当前菜单是否展开
            boolean spread = (permission.getSpread()==null || permission.getSpread()==1)? true:false;
            //将菜单信息保存到node集合中
            treeNodes.add(new TreeNode(permission.getId(),permission.getPid(),permission.getTitle(),spread,checkArr));

        }
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 分配权限
     * @param rid
     * @param pid
     * @return
     */
    @RequestMapping("/saveRolePermission")
    public String grantPermission(Integer rid,String pid){
        if(roleService.saveRolePermission(rid,pid)){
            return JSON.toJSONString(RestResponse.ok("权限分配成功"));
        }else {
            return JSON.toJSONString(RestResponse.fail("权限分配失败"));
        }
    }

}
