package com.test.movie.repository;

import com.test.movie.entity.Member;
import com.test.movie.entity.Movie;
import com.test.movie.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    //Review를 처리할떄 member도 같이 로딩할수있도록
    @EntityGraph(attributePaths = {"member"}) //default EntityGraphType.FETCH;
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review r where r.member = :member")
    void deleteByMember(Member member);
}
