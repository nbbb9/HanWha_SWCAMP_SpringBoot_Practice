package com.example.encore_spring_pjt.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller//객체생성을 통한 위임
public class IndexController {
    @RequestMapping("/index.hanwha")
    public String index(){
        System.out.println("debug >>> IndexController client path");
        return "index";
    }
}
