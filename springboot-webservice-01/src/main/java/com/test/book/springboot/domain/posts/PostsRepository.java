package com.test.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository<Entity클래스,Pk타입>상속하면 기본적인 CRUD메소드가 자동으로 생성
//Entity클래스와 함께위치해야한다
public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY  p.id DESC ")
    List<Posts> findAllDesc();

}
