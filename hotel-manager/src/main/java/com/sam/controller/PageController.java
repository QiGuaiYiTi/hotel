package com.sam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : 页面跳转控制器
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    /**
     * 跳转到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager.html")
    public String toDeptManager(){
        return "dept/deptManager";
    }

}
