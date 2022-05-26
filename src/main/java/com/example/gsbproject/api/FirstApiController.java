package com.example.gsbproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



// 기존 jsp mvc 프로젝트 한거와의 차이점 기존 프로젝트나 해당 프로젝트의 일반컨트롤러는 html 뷰 템플릿을 통째로 response하는 구조였다.
// RestController 의 경우는 json 을 반환 오히려 예전에한 예약프로젝트가 구성이 복잡하고 코딩이 더 어려웠다.



@RestController // RestAPI용 컨트롤러 json 반환
public class FirstApiController {


    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }

}
