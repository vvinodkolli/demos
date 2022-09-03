package com.netflix.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NetflixMovieRequest {
    private String title;
    private String type;
    private String description;
    private Integer releaseYear;
    private String ageCertification;
    private Integer runtime;
    private List<String> genres;
    private List<String> productionCountries;
    private Double imdbScore;
}
