package com.example.encore_spring_pjt.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    // http://server_ip:port/
    // return type : void, String, Model, ModelAndView : full browsing
    // return type : XXXXXDTO, List, Map, Set , ResponseEntity : Rest API Service
    // @RequestMapping("/")
    // public ModelAndView index(){
    //     System.out.println(">>> TestController path / , callback function Index()");
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("index");
    //     mv.addObject("msg", "hi~, LYW");
    //     return mv;

    @RequestMapping("/")
    public String index(Model model){
        System.out.println(">>> TestController path / , callback function Index()");
        model.addAttribute("msg", "welcome to SpringBoot with JSP");
        return "index";
    }
    @RequestMapping("/test")
    public void test(){
        System.out.println(">>> TestController path / test , callback function ");
    }
    
}