package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.sam.pojo.Permission;
import com.sam.pojo.SysUser;
import com.sam.service.PermissionService;
import com.sam.service.SysUserService;
import com.sam.utils.MenuNode;
import com.sam.utils.TreeNode;
import com.sam.utils.TreeUtil;
import com.sam.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/loadIndexMenuLeft")
    public String loadIndexMenuLeft(PermissionVo permissionVo, Principal principal){
        //定义集合用户保存菜单数据
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        //定义集合用户保存首页数据
        LinkedHashMap<String, Object> homeInfo = new LinkedHashMap<>();
        //定义集合用户保存logo数据
        LinkedHashMap<String, Object> logoInfo = new LinkedHashMap<>();

        //获取当前登录用户
        SysUser loginUser = sysUserService.findSysUserByUserName(principal.getName());
        //根据当前用户，查询当前用户的菜单权限集合
        List<Permission> permissionListByUserId = permissionService.findPermissionListByUserId(loginUser.getId());
        //调用方法查询菜单集合
         // permissionVo.setType("menu");
        //List<Permission> menuList = permissionService.findPermissionList(permissionVo);
        //创建集合保存menuNode
        List<MenuNode> nodeList = new ArrayList<MenuNode>();
        //遍历菜单集合
        for (Permission permission : permissionListByUserId) {
            //创建menuNode对象
            MenuNode menuNode = new MenuNode();
            menuNode.setId(permission.getId());//菜单编号
            menuNode.setPid(permission.getPid());//父级菜单编号
            menuNode.setTitle(permission.getTitle());//标题
            menuNode.setHref(permission.getHref());//跳转地址
            menuNode.setIcon(permission.getIcon());//菜单图标
            menuNode.setSpread(permission.getSpread());//是否展开
            menuNode.setTarget(permission.getTarget());//打开方式
            //存入集合中
            nodeList.add(menuNode);
        }
        //保存menuInfo信息
        map.put("menuInfo", TreeUtil.toTree(nodeList,0));
        //保存HomeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/toYearTotalPriceManager.html");//跳转地址
        //保存logoInfo信息
        logoInfo.put("title","酒店管理系统");//logo标题
        logoInfo.put("image","/statics/layui/images/logo.png");//logo图片
        logoInfo.put("href","/index.jsp");//首页地址
        //保存所有信息
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);

        return JSON.toJSONString(map);
    }

}
