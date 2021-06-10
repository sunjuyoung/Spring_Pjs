package com.test.springboot02.service;

import com.test.springboot02.dto.MovieDTO;
import com.test.springboot02.dto.MovieImageDTO;
import com.test.springboot02.entity.Movie;
import com.test.springboot02.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    default Map<String,Object> dtoToEntity(MovieDTO movieDTO){
        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .id(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie",movie);
        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() >0){
            List<MovieImage> movieImages = imageDTOList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .uuid(movieImageDTO.getUuid())
                        .imgName(movieImageDTO.getImgName())
                        .path(movieImageDTO.getPath())
                        .movie(movie)
                        .build();
                return movieImage;

            }).collect(Collectors.toList());
            entityMap.put("imgList",movieImages);
        }

        return entityMap;
    }
}
