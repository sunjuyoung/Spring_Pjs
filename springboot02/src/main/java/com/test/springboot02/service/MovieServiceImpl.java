package com.test.springboot02.service;

import com.test.springboot02.dto.MovieDTO;
import com.test.springboot02.entity.Movie;
import com.test.springboot02.entity.MovieImage;
import com.test.springboot02.repository.MovieImageRepository;
import com.test.springboot02.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{


    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String,Object> movieList =  dtoToEntity(movieDTO);
        Movie movie = (Movie) movieList.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) movieList.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(m->{
            movieImageRepository.save(m);
        });


        return null;
    }
}
