package com.example.buildbaseframe.api.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/6/25 15:19
 * @version: 1.0
 */
@Controller
public class SecurityController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/content")
    public String content(){
        return "content";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
