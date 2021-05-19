package com.sam.service.impl;

import com.alibaba.fastjson.JSON;
import com.sam.dao.DeptMapper;
import com.sam.pojo.Dept;
import com.sam.service.DeptService;
import com.sam.utils.JedisPoolUtils;
import com.sam.utils.SystemConstant;
import com.sam.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

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
     * 查询部门列表（用于用户管理页面加载部门下拉列表）
     * @return
     */
    @Override
    public String selectDeptList() {
        //获取jedis对象
        Jedis jedis = JedisPoolUtils.getJedis();
        //先从Redis缓存里读取
        String deptList = jedis.get(SystemConstant.DEPTLIST);
        //若缓存中读取不到数据
        if(StringUtils.isEmpty(deptList)){
            //再从数据库查询
            List<Dept> depts = deptMapper.selectDeptList();
            //将数据转换为JSON
            deptList = JSON.toJSONString(depts);
            //将查询到的数据保存到缓存中
            jedis.set(SystemConstant.DEPTLIST, deptList);
        }

        //返回数据
        return deptList;
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
        //添加部门
        int count = deptMapper.insertDept(dept);
        //如果添加成功。则清空缓存
        if(count>0){
            JedisPoolUtils.getJedis().del(SystemConstant.DEPTLIST);
        }
        return count;
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @Override
    public int updateDept(Dept dept) {
        //修改部门
        int count = deptMapper.updateDept(dept);
        //如果修改成功。则清空缓存
        if(count>0){
            JedisPoolUtils.getJedis().del(SystemConstant.DEPTLIST);
        }
        return count;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public int deleteDeptById(Integer id) {
        //删除部门
        int count = deptMapper.deleteDeptById(id);
        //如果删除成功，就清空部门信息的缓存
        if(count>0){
            JedisPoolUtils.getJedis().del(SystemConstant.DEPTLIST);
        }
        return count;
    }
}
