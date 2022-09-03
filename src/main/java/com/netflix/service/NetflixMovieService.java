package com.netflix.service;

import com.netflix.dto.NetflixMovieRequest;
import com.netflix.dto.NetflixMovieResponse;
import com.netflix.model.Movie;
import com.netflix.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetflixMovieService {

    private MovieRepository movieRepository;

    public NetflixMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public NetflixMovieResponse createMovie(NetflixMovieRequest netflixMovieRequest) {
        Movie movie = movieRepository.save(Movie.builder()
                .ageCertification(netflixMovieRequest.getAgeCertification())
                .type(netflixMovieRequest.getType())
                .description(netflixMovieRequest.getDescription())
                .genres(netflixMovieRequest.getGenres())
                .imdbScore(netflixMovieRequest.getImdbScore())
                .productionCountries(netflixMovieRequest.getProductionCountries())
                .runtime(netflixMovieRequest.getRuntime())
                .title(netflixMovieRequest.getTitle())
                .year(netflixMovieRequest.getReleaseYear())
                .build());
        return NetflixMovieResponse.builder()
                .id(movie.getId().intValue())
                .type(movie.getType())
                .ageCertification(movie.getAgeCertification())
                .description(movie.getDescription())
                .genres(movie.getGenres())
                .imdbScore(movie.getImdbScore())
                .productionCountries(movie.getProductionCountries())
                .runtime(movie.getRuntime())
                .title(movie.getTitle())
                .releaseYear(movie.getYear())
                .build();
    }

    public NetflixMovieResponse updateMovieDetails(String title, NetflixMovieRequest netflixMovieRequest) {
        Movie movie = movieRepository.findByTitle(title);

        if (ObjectUtils.isEmpty(movie)) {
            throw new RuntimeException("No Movie found with title: " + title);
        }

        if (StringUtils.hasText(netflixMovieRequest.getTitle())) {
            movie.setTitle(netflixMovieRequest.getTitle());
        }

        if (StringUtils.hasText(netflixMovieRequest.getDescription())) {
            movie.setDescription(netflixMovieRequest.getDescription());
        }

        if (netflixMovieRequest.getImdbScore() != null) {
            movie.setImdbScore(netflixMovieRequest.getImdbScore());
        }


        return NetflixMovieResponse.builder()
                .id(movie.getId().intValue())
                .type(movie.getType())
                .ageCertification(movie.getAgeCertification())
                .description(movie.getDescription())
                .genres(movie.getGenres())
                .imdbScore(movie.getImdbScore())
                .productionCountries(movie.getProductionCountries())
                .runtime(movie.getRuntime())
                .title(movie.getTitle())
                .releaseYear(movie.getYear())
                .build();
    }

    public boolean deleteMovie(String title) {
        return movieRepository.deleteByTitle(title) > 0;
    }

    public List<NetflixMovieResponse> getAllMovies(){
        List<Movie> netflixMovieResponses = movieRepository.findAll();
        return netflixMovieResponses.stream().map(movie -> NetflixMovieResponse.builder()
                    .id(movie.getId().intValue())
                    .type(movie.getType())
                    .ageCertification(movie.getAgeCertification())
                    .description(movie.getDescription())
                    .genres(movie.getGenres())
                    .imdbScore(movie.getImdbScore())
                    .productionCountries(movie.getProductionCountries())
                    .runtime(movie.getRuntime())
                    .title(movie.getTitle())
                    .releaseYear(movie.getYear())
                    .build())
                .collect(Collectors.toList());
    }

    public NetflixMovieResponse getMovie(String title){
        Movie movie = movieRepository.findByTitle(title);

        if (ObjectUtils.isEmpty(movie)) {
            throw new RuntimeException("No Movie found with title: " + title);
        }

        return NetflixMovieResponse.builder()
                .id(movie.getId().intValue())
                .type(movie.getType())
                .ageCertification(movie.getAgeCertification())
                .description(movie.getDescription())
                .genres(movie.getGenres())
                .imdbScore(movie.getImdbScore())
                .productionCountries(movie.getProductionCountries())
                .runtime(movie.getRuntime())
                .title(movie.getTitle())
                .releaseYear(movie.getYear())
                .build();
    }
}
