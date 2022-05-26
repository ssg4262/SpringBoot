package com.example.gsbproject.repository;

import com.example.gsbproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> { // jpa 에서 제공하는 걸 상속
// <안에>관리 대상 entity를 넣고 관리하는 id의 타입인 long을 넣는다
//CrudRepository 안엔 아이디를 세이브하는 기능 , 찾는기능 등등 미리 만들어진걸 상속

    @Override // Iterable  타입 반환을 재정의
    ArrayList<Article> findAll();
}
