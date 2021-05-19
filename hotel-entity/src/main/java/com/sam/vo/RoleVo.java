package com.sam.vo;

import com.sam.pojo.Role;
import lombok.Data;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Data
public class RoleVo extends Role {

    private Integer page;
    private Integer limit;

}
