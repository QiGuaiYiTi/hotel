package com.sam.service.impl;

import com.sam.dao.DeptMapper;
import com.sam.pojo.Dept;
import com.sam.service.DeptService;
import com.sam.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门列表（对应后台部门列表展示,以及根据部门名称模糊查询）
     * @param deptVo
     * @return
     */
    @Override
    public List<Dept> findDeptList(DeptVo deptVo) {
        return deptMapper.findDeptList(deptVo);
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Override
    public int insertDept(Dept dept) {
        //设置创建时间
        dept.setCreateDate(new Date());
        return deptMapper.insertDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public int deleteDeptById(Integer id) {
        return deptMapper.deleteDeptById(id);
    }
}
