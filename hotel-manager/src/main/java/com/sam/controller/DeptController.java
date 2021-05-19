package com.sam.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.pojo.Dept;
import com.sam.service.DeptService;
import com.sam.utils.DataGridViewResult;
import com.sam.utils.RestResponse;
import com.sam.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@Controller
@RequestMapping("/admin/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表（对应后台部门列表展示,以及根据部门名称模糊查询）
     * @param deptVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo){
        //设置分页信息
        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
        //查询部门集合
        List<Dept> deptList = deptService.findDeptList(deptVo);
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 加载用户管理页面模糊查询中部门下拉列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDeptList")
    public String findDeptList(){
         return deptService.selectDeptList();
    }



    /**
     * 添加部门
     * @return
     */
    @ResponseBody
    @RequestMapping("/addDept")
    public String addDept(Dept dept){
        if(deptService.insertDept(dept)>0){
            //添加成功，返回数据
            RestResponse ok = RestResponse.ok("部门添加成功");
            return JSON.toJSONString(ok);
        }else{
            RestResponse fail = RestResponse.fail("部门添加失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept){
        if(deptService.updateDept(dept)>0){
            //修改成功，返回成功数据
            RestResponse ok = RestResponse.ok("部门修改成功");
            return JSON.toJSONString(ok);
        }else{
            //修改失败，返回失败数据
            RestResponse fail = RestResponse.fail("部门修改失败");
            return JSON.toJSONString(fail);
        }
    }

    /**
     * 根据部门编号删除部门
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public String deleteDeptById(Integer id){
        if(deptService.deleteDeptById(id)>0){
            RestResponse ok = RestResponse.ok("部门删除成功");
            return JSON.toJSONString(ok);
        }else {
            RestResponse fail = RestResponse.fail("部门删除失败");
            return JSON.toJSONString(fail);
        }
    }

}
