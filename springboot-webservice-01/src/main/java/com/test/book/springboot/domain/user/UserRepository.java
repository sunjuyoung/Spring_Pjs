package com.test.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //로그인으로 반환되는 값중 email를 통해 사용자 판단
    Optional<User> findByEmail(String email);
}
