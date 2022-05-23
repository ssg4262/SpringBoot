package com.example.gsbproject.controller;

import com.example.gsbproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 주의 컨트롤러시작할때 항상 선언
public class ArticleController {


    @GetMapping("/articles/new")
    public String newArticleForm(){ // 폼리턴 메서드
         return "/articles/new";


    }
    @PostMapping("/articles/create") // 포스트 방식으로 던져서 요청을 포스트 매핑으로 받아야함
    public String createArticle(ArticleForm form){
        ///sout tap
        System.out.println(form);//확인용


        return "";
    }

}
