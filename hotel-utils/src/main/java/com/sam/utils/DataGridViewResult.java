package com.sam.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 数据返回工具类
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@Data
@NoArgsConstructor
public class DataGridViewResult {
    private Integer code = 0;
    private String message = "";
    private Long count;
    private Object data;

    public DataGridViewResult(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridViewResult(Object data) {
        this.data = data;
    }
}
