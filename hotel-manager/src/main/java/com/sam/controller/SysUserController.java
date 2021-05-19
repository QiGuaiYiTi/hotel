package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.pojo.SysUser;
import com.sam.service.RoleService;
import com.sam.service.SysUserService;
import com.sam.utils.DataGridViewResult;
import com.sam.utils.RestResponse;
import com.sam.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Autowired
    private RoleService roleService;

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清空session
        session.invalidate();
        return "redirect:/";

    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPwd")
    public String resetPwd(Integer id){
        if(sysUserService.resetPwd(id)>0){
            RestResponse ok = RestResponse.ok("重置密码成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("重置密码失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 根据部门编号查询该部门的员工信息（用户删除部门之前的校验）
     * @param deptId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkDeptHasUser")
    public String checkDeptHasUser(Integer deptId){
        //查询当前部门下是否存在员工信息
        List<SysUser> sysUsers = sysUserService.selectSysUserByDeptId(deptId);
        if(sysUsers!=null && sysUsers.size()>0){
            RestResponse exist = RestResponse.exist("该部门下存在员工信息，无法删除部门");
            return JSON.toJSONString(exist);
        }else {
            RestResponse notExist = RestResponse.notExist(null);
            return JSON.toJSONString(notExist);
        }
    }

    /**
     * 分页查询员工列表，并实现可模糊查询
     * @param sysUserVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public DataGridViewResult list(SysUserVo sysUserVo, Principal principal){
        //获取当前登录用户
        SysUser loginUser = sysUserService.findSysUserByUserName(principal.getName());
        //判断当前用户是否为空以及用户类型，2表示普通用户
        if(loginUser!=null && loginUser.getUserType()==2){
            //普通用户只能查看普通用户信息
            sysUserVo.setUserType(2);
        }

        //设置分页信息
        PageHelper.startPage(sysUserVo.getPage(),sysUserVo.getLimit());
        //调用查询列表的方法
        List<SysUser> sysUserList = sysUserService.selectSysUserListByPage(sysUserVo);
        //创建分页信息对象
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(sysUserList);
        //返回视图显示结果
        return new DataGridViewResult(sysUserPageInfo.getTotal(),sysUserPageInfo.getList());
    }

    /**
     * 添加员工
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public String addSysUser(SysUser sysUser, Principal Principal){
        //获取当前用户信息
        String principalName = Principal.getName();
        //查询当前用户id
        SysUser currentUser = sysUserService.findSysUserByUserName(principalName);
        //设置创建人id
        sysUser.setCreatedBy(currentUser.getId());
        if(sysUserService.addSysUser(sysUser)>0){
            RestResponse ok = RestResponse.ok("员工添加成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("员工添加失败");
            return JSON.toJSONString(fail);

        }
    }

    /**
     * 修改员工
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateSysUser(SysUser sysUser, Principal Principal){
        //获取当前用户信息
        String principalName = Principal.getName();
        //查询当前用户
        SysUser currentUser = sysUserService.findSysUserByUserName(principalName);
        //设置创建人id
        sysUser.setModifyBy(currentUser.getId());
        if(sysUserService.addSysUser(sysUser)>0){
            RestResponse ok = RestResponse.ok("员工修改成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("员工修改失败");
            return JSON.toJSONString(fail);

        }
    }

    /**
     * 删除员工，同时删除员工角色关系
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public String deleteSysUser(Integer id){
       if(sysUserService.removeSysUser(id)>0){
           RestResponse ok = RestResponse.ok("员工删除成功");
           return JSON.toJSONString(ok);
       }else{
           RestResponse fail = RestResponse.fail("员工删除失败");
           return JSON.toJSONString(fail);

       }
    }

    /**
     * 分配角色之前的初始化角色列表功能
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/initRoleListByUserId")
    public DataGridViewResult initRoleListByUserId(Integer userId){
        //定义变量,标识是否选中
        boolean flag = false;
        //先查询所有角色
        List<Map<String, Object>> maps = roleService.selectRoleListOnly();
        //查询当前用户已经拥有的角色编号
        List<Integer> list = roleService.selectRoleIdBySysUserId(userId);
        //循环遍历所有角色
        for (Map<String, Object> map : maps) {
            //获取每一个角色的id
            Integer id = (Integer) map.get("id");
            //内层遍历当前用户已经拥有的角色
            for (Integer roleId : list) {
                if(roleId==id){
                    flag = true;
                    break;
                }

            }
            //将状态标识保存到map集合中
            map.put("LAY_CHECKED",flag);
        }
        //返回数据
        return new DataGridViewResult(maps);
    }

    /**
     * 分配角色
     * @param ids
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/grantRole")
    public String grantRole(String ids,Integer userId){
        if(sysUserService.saveSysUserRole(ids,userId)){
            RestResponse ok = RestResponse.ok("分配角色成功");
            return JSON.toJSONString(ok);
        }else {
            RestResponse fail = RestResponse.fail("分配角色失败");
            return JSON.toJSONString(fail);
        }
    }

}
