package com.example.gsbproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")//부를이름 예전에 배운 서블릿 매핑명이랑 동일
public String niceToMeetYou(Model model){
    model.addAttribute("username","고승범");//username 부르면 고승범이라는 값나옴 //너무 기초

    return "greetings"; // greetings.mustache 파일찾아서 동작


}
@Controller
public class Bye{
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","gsb");
        return "goodbye";// 보여줄 뷰 페이지
    }

}



}
