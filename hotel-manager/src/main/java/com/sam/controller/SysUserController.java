package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.sam.pojo.SysUser;
import com.sam.service.SysUserService;
import com.sam.utils.RestResponse;
import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * 根据用户名查询用户，用户登录时用户名输入框失去焦点时检查用户名是否存在
     * @param userName
     * @return
     */
    @RequestMapping("/checkUserNameExist")
    public String checkUserNameExist(String userName){
        //调用方法查询
        SysUser sysUser = sysUserService.findSysUserByUserName(userName);
        return JSON.toJSONString(sysUser);
    }

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
     * 根据部门编号查询该部门的员工信息
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

}
