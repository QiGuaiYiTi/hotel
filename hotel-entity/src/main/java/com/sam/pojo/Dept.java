package com.sam.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String deptName;
    private String address;
    private Date createDate;
    private String remark;




}