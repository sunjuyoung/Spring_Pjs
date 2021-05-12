package com.test.springboot02.repository;

import com.test.springboot02.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository  extends JpaRepository<Movie,Long> {


    @Query("select m , mi ,count(distinct r),avg(coalesce(r.grade,0)) " +
            " from Movie m " +
            " left outer join MovieImage mi on m = mi.movie  " +
            " left outer join Review r on m = r.movie group by m,mi")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, mi, count(distinct r) ,  AVG(COALESCE(r.grade,0))" +
            " from Movie m left outer join MovieImage mi on m = mi.movie" +
            " left join Review r on r.movie = m " +
            " where m.id = :movieId" +
            " group by m,mi")
    List<Object[]> getMovieWithAll(@Param("movieId") Long movieId);
}
