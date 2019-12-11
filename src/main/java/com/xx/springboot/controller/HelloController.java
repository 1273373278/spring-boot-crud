package com.xx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shkstart
 * @create 2019-12-11 19:20
 */

@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}