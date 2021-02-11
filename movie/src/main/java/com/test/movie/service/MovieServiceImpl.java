package com.test.movie.service;

import com.test.movie.dto.MovieDTO;
import com.test.movie.entity.Movie;
import com.test.movie.entity.MovieImage;
import com.test.movie.repository.MovieImageRepository;
import com.test.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String,Object> entityMap = dtoToEntity(movieDTO);

        Movie movie = (Movie)entityMap.get("movie");

        List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> movieImageRepository.save(movieImage));


        return movie.getMno();
    }
}
