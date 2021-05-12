package com.test.springboot02.repository;

import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.Movie;
import com.test.springboot02.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void insertDummy(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Long movieId = (long)(Math.random()*100)+1;
            Long memberId = (long)(Math.random()*100)+1;

            Movie movie = Movie.builder().id(movieId).build();
            Member member = Member.builder().id(memberId).build();

            int grade = (int)(Math.random()*5)+1;

            Review review = Review.builder().content("testReview "+i)
                    .grade(grade)
                    .member(member)
                    .movie(movie)
                    .build();

            reviewRepository.save(review);

        });
    }

    @Test
    public void list(){
        Movie movie = Movie.builder().id(7L).build();

        List<Review> byMovie = reviewRepository.findByMovie(movie);
        for(Review r : byMovie){
            System.out.println(r.getContent());
            System.out.println(r.getMember().getNickname());
        }
    }

    @Transactional
    @Test
    public void delete(){
        Long mid = 7L;
        Member member = Member.builder().id(mid).build();
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }

}