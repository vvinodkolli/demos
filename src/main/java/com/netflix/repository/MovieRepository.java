package com.netflix.repository;

import com.netflix.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, Long> {
    Movie findByTitle(String title);

    Long deleteByTitle(String title);
}
