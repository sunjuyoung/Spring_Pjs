package com.test.movie.repository;

import com.test.movie.entity.Movie;
import com.test.movie.entity.MovieImage;
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
    MovieRepository movieRepository;

    @Autowired
    MovieImageRepository movieImageRepository;


    @Commit
    @Transactional
    @Test
    public void insert(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Movie movie = Movie.builder().title("test"+i).build();

            movieRepository.save(movie);

            int count = (int)(Math.random()*4)+1;

            for(int j=0; j<count; j++){
                MovieImage image = MovieImage.builder()
                        .imgName("test"+j+".jpg")
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .build();

                movieImageRepository.save(image);
            }
        });
    }

    @Test
    public void pageList(){

        PageRequest pageRequest = PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"mno"));

        Pageable pageable = PageRequest.of(0,10, Sort.by("mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for(Object[] arr : result.getContent()){
            System.out.println(Arrays.toString(arr));
        }

    }

    @Test
    public void movieWithAll(){
        List<Object[]> result = movieRepository.getMovieWithAll(1L);

        for(Object[] arr: result){
            System.out.println(Arrays.toString(arr));
        }
    }

}