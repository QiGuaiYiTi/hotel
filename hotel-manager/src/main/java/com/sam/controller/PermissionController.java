package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.sam.pojo.Permission;
import com.sam.service.PermissionService;
import com.sam.utils.DataGridViewResult;
import com.sam.utils.RestResponse;
import com.sam.utils.TreeNode;
import com.sam.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/20
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 加载菜单树
     * @return
     */
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //查询所有权限
        List<Permission> permissionList = permissionService.findPermissionList(null);
        //创建集合保存节点信息
        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();

        //循环遍历菜单列表集合
        for (Permission permission : permissionList) {
            //判断当前菜单是否展开
            boolean spread = (permission.getSpread()==null || permission.getSpread()==1)? true:false;
            //将菜单信息保存到node集合中
            treeNodes.add(new TreeNode(permission.getId(),permission.getPid(),permission.getTitle(),spread));
        }
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 分页查询菜单
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult getMenuList(PermissionVo permissionVo){
        //设置分页信息
        PageHelper.startPage(permissionVo.getPage(),permissionVo.getLimit());
        //调用查询菜单列表的方法
        List<Permission> permissionList = permissionService.findPermissionList(permissionVo);
        //创建分页对象
        PageInfo<Permission> permissionPageInfo = new PageInfo<>(permissionList);
        //返回数据
        return new DataGridViewResult(permissionPageInfo.getTotal(),permissionPageInfo.getList());
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        if(permissionService.addPermission(permission)>0){
            RestResponse ok = RestResponse.ok("菜单添加成功");
            return JSON.toJSONString(ok);
        }else {
            RestResponse fail = RestResponse.fail("菜单添加失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    @RequestMapping("/updatePermission")
    public String updatePermission(Permission permission){
        if(permissionService.modifyPermission(permission)>0){
            RestResponse ok = RestResponse.ok("菜单修改成功");
            return JSON.toJSONString(ok);
        }else {
            RestResponse fail = RestResponse.fail("菜单修改失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 检查是否存在子菜单
     * @param id
     * @return
     */
    @RequestMapping("/checkPermission")
    public String checkPermissionById(Integer id) {
            if(permissionService.findPermissionByPId(id)>0){
                RestResponse exist = RestResponse.exist("当前菜单下存在子菜单，无法删除");
                return JSON.toJSONString(exist);
            }else {
                RestResponse notExist = RestResponse.notExist("");
                return JSON.toJSONString(notExist);
            }
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String removePermission(Integer id) {
        if(permissionService.removePermission(id)>0){
            return JSON.toJSONString(RestResponse.ok("菜单删除成功"));
        }else {
            return JSON.toJSONString(RestResponse.fail("菜单删除失败"));
        }
    }


}
