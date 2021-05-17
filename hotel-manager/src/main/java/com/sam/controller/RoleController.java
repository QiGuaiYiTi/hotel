package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.pojo.Role;
import com.sam.service.RoleService;
import com.sam.utils.DataGridViewResult;
import com.sam.utils.RestResponse;
import com.sam.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
