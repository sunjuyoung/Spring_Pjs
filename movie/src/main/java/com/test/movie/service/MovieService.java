package com.test.movie.service;

import com.test.movie.dto.MovieDTO;
import com.test.movie.dto.MovieImageDTO;
import com.test.movie.entity.Movie;
import com.test.movie.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MovieService {

    Long register(MovieDTO movieDTO);


    //Movie 객체 MovieImage객체를 한번에 Map타입으로 반환
    default Map<String ,Object>dtoToEntity(MovieDTO movieDTO){
        Map<String,Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        
        entityMap.put("movie",movie);

        //movieImage객체 처리
        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() >0){
            List<MovieImage> movieImageList = new ArrayList<>();

            for(int i=0; i<imageDTOList.size(); i++){
                MovieImage movieImage = MovieImage.builder()
                        .movie(movie)
                        .path(imageDTOList.get(i).getPath())
                        .imgName(imageDTOList.get(i).getImgName())
                        .uuid(imageDTOList.get(i).getUuid())
                        .build();
                movieImageList.add(movieImage);
            }
            //
            entityMap.put("imgList",movieImageList);
        }
        return entityMap;
    }
}
