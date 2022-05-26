package com.example.gsbproject.api;

import com.example.gsbproject.dto.ArticleForm;
import com.example.gsbproject.entity.Article;
import com.example.gsbproject.repository.ArticleRepository;
import com.example.gsbproject.service.ArticleService;
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
@Autowired // DI 생성 객체를 가져와 연결
private ArticleService articleService;

//    @Autowired
//    private ArticleRepository articleRepository;
//
//
//
//
//    // get 읽기,가져오기
        @GetMapping("/api/articles")
        public List<Article> index() {   // 기초중에 기초 당연히 여러개가 될수있고 얼마나  늘어날지 모르니 리스트로 받기
            return articleService.index();
    }
   @GetMapping("/api/articles/{id}")
   public Article show(@PathVariable  Long id){   //url 로 가져오는 요청은 @PathVariable  Long id 가져오기 예전엔 request.getparameter같은 맥락
       return articleService.show(id);
   }


   //post 생성
   @PostMapping("/api/articles")
        public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ // RequestBody 에 드가야해서 어노테이션을 넣어주자
            Article created = articleService.create(dto);


      // Article article = dto.toEntity();//entity로 변환해서 넣어야함 요청시엔 entity가 아니기때문에
      return (created !=null) ? // 삼항 연산자 사용
              ResponseEntity.status(HttpStatus.OK).body(created):
              ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }
//
//
//
//
//     //PATCH 수정
    @PatchMapping("/api/articles/{id}")
      public ResponseEntity<Article> create(@PathVariable  Long id,
                                            @RequestBody ArticleForm dto){  // 수정은 당연히 조건이 해당아이디고 해당아이디의 내용을 요청을 받아 재생성해야하기때문에

       Article updated  =  articleService.update(id,dto);
           return   (updated != null ) ?
                   ResponseEntity.status(HttpStatus.OK).body(updated) :
                   ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            


      }


      //DELETE
     @DeleteMapping("api/articles/{id}")
     public ResponseEntity<Article>  delete(@PathVariable Long id){
      Article deleted = articleService.delete(id);

      return ( deleted!=null )?    // null이 아니면 즉 삭제 됬다면
             ResponseEntity.status(HttpStatus.OK).body(deleted) :
             ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

   }
             //트랙젝션 -> 실패 -> 롤백!
        @PostMapping("/api/transaction-test")
        public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
          List<Article>  createdList =   articleService.createArticles(dtos);
            return (createdList!=null)?
                    ResponseEntity.status(HttpStatus.OK).body(createdList) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();



        }



        }

