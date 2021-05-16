package com.sam.vo;

import com.sam.pojo.Dept;
import lombok.Data;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@Data
public class DeptVo extends Dept {

    private Integer page;
    private Integer limit;

}
