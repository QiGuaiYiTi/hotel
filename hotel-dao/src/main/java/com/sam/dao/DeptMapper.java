package com.sam.dao;

import com.sam.pojo.Dept;
import com.sam.vo.DeptVo;

import java.util.List;

public interface DeptMapper {

    /**
     * 查询部门列表（对应后台部门列表展示,以及根据部门名称模糊查询）
     * @param deptVo
     * @return
     */
    List<Dept> findDeptList(DeptVo deptVo);

    /**
     * 添加部门
     * @param dept
     * @return
     */
    int insertDept(Dept dept);

    /**
     * 根据部门编号修改部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 根据部门编号删除部门
     * @param id
     * @return
     */
    int deleteDeptById(Integer id);
}
