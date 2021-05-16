package com.sam.vo;

import com.sam.pojo.SysUser;
import lombok.Data;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Data
public class SysUserVo extends SysUser {


    private Integer page;
    private Integer limit;

}
