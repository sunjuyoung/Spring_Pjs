package com.test.movie.repository;

import com.test.movie.entity.Movie;
import com.test.movie.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByMovie(Movie movie);
}
