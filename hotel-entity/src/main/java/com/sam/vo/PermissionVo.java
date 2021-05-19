package com.sam.vo;

import com.sam.pojo.Permission;
import lombok.Data;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@Data
public class PermissionVo extends Permission {

    private Integer page;
    private Integer limit;

}
