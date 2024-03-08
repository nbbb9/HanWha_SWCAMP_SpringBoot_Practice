package com.example.encore_spring_pjt.ctrl.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/user")

public class UserController {
    @PostMapping("/login.hanwha")
    public String login(UserRequest params){
    //public String login(String id, String pwd){
    //public String login(@RequestParam(name = "id")String id, @RequestParam(name = "pwd") String pwd){
        System.out.println("debug UserController client path /user//...");
        System.out.println("User ID >>> " + params.getId());
        System.out.println("User PWD>>> " + params.getPwd());
        return "redirect:/board/list.hanwha";
    }
    
}
