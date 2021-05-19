package com.sam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : 页面跳转控制器
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    /**
     * 跳转到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager.html")
    public String toDeptManager(){
        return "dept/deptManager";
    }

    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager.html")
    public String toRoleManager(){
        return "role/roleManager";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/toUserManager.html")
    public String toUserManager(){
        return "user/userManager";
    }

    /**
     * 跳转到权限管理页面
     * @return
     */
    @RequestMapping("/toPermissionManager.html")
    public String toPermissionManager(){
        return "permission/permissionManager";
    }

}
