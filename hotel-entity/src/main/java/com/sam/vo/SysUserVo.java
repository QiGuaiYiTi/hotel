package com.sam.vo;

import com.sam.pojo.SysUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
