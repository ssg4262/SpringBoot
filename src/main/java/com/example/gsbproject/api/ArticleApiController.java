package com.example.gsbproject.api;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import com.example.gsbproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j // 로그확인
@RestController // 데이터를 json 형태로 반환
public class ArticleApiController {
        // 기본중의 기본 3달전에 배운거 구현이 달라 못알아들었을뿐 쉽게 생각하자
    @Autowired
    private ArticleRepository articleRepository;




    // get 읽기,가져오기
    @GetMapping("/api/articles")
    public List<Article> index(){   // 기초중에 기초 당연히 여러개가 될수있고 얼마나  늘어날지 모르니 리스트로 받기
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable  Long id){   //url 로 가져오는 요청은 @PathVariable  Long id 가져오기 예전엔 request.getparameter같은 맥락
        return articleRepository.findById(id).orElse(null);
    }




    //post 생성
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ // RequestBody 에 드가야해서 어노테이션을 넣어주자
        Article article = dto.toEntity();//entity로 변환해서 넣어야함 요청시엔 entity가 아니기때문에
        return articleRepository.save(article);
    }




     //PATCH 수정
        @PatchMapping("/api/articles/{id}")
        public ResponseEntity<Article> create(@PathVariable  Long id,
                                              @RequestBody ArticleForm dto){  // 수정은 당연히 조건이 해당아이디고 해당아이디의 내용을 요청을 받아 재생성해야하기때문에
            
        
        //1: 수정용 엔티티 생성
            Article article = dto.toEntity(); // 요청이 들어올땐 db로 보낼수있는 enttity 가아니기 때문에 //dto 는 요청받은값
            log.info("id:{} , article:{}",id,article.toString());


        //2: 수정 대상 엔티티 를 db 에서 조회
        Article target = articleRepository.findById(id).orElse(null); //<- 는 db에서 조회한거  / 위엔 요청받은거 차이점
        
        //3: 잘못된 요청 처리 
        if(target==null|| id !=article.getId()){
            //400 잘못된요청 응답
            log.info("잘못된 응답id:{} , article:{}",id,article.toString());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        //4: 업데이트 및 정상 응답
            target.patch(article); // 아무것도 입력안할시에 기존에 있는걸 넣기
           Article updated =  articleRepository.save(target);
        
        
        
        
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }




        //DELETE
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id){
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        
        
        //잘못된 요청 처리

         if(target == null){ // 데이터가없으면 못지우니
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
         }

        //대상삭제
        articleRepository.delete(target);

        
        return ResponseEntity.status(HttpStatus.OK).build();
    }








}
