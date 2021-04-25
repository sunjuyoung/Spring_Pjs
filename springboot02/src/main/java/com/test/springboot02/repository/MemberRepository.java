package com.test.springboot02.repository;

import com.test.springboot02.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @EntityGraph(attributePaths={"roleSet"},type= EntityGraph.EntityGraphType.LOAD)
    Optional<Member> findByNicknameAndFromSocial(String nickname, boolean fromSocial);

    Member findByNickname(String nickname);
}
