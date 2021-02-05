package com.test.movie.repository;

import com.test.movie.entity.Member;
import com.test.movie.entity.Movie;
import com.test.movie.entity.MovieImage;
import com.test.movie.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void insert(){

        IntStream.rangeClosed(1,200).forEach(i->{

            Long mid = ((long)(Math.random()*99)+1);
            Long mno = ((long)(Math.random()*99)+1);

            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .comment("영화 할줄평..."+i)
                    .grade((int)(Math.random()*5)+1)
                    .build();

            reviewRepository.save(review);
        });
    }


    @Test
    public void getMovieReview(){
        Movie movie = Movie.builder().mno(1L).build();

        List<Review> result = reviewRepository.findByMovie(movie);
        for(Review a : result){
            System.out.println(a.getComment());
            System.out.println(a.getGrade());
            System.out.println(a.getReviewnum());
            System.out.println(a.getMember().getEmail());
        }
    }

}