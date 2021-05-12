package com.test.springboot02.repository;

import com.test.springboot02.entity.Movie;
import com.test.springboot02.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertDubmmy(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Movie movie =Movie.builder().title("movie"+i).build();

            movieRepository.save(movie);

            for(int j=0; j<2; j++){
                MovieImage movieImage = MovieImage.builder()
                        .movie(movie).imgName("test"+j+".jpg").uuid(UUID.randomUUID().toString()).build();
                movieImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void list(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());
        Page<Object[]> result = movieRepository.getListPage(pageable);

        for(Object[] objects : result){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void getMovie(){

        List<Object[]> result = movieRepository.getMovieWithAll(7L);

        for(Object[] objects : result){
            System.out.println(Arrays.toString(objects));
        }
    }

}