package com.netflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "netflix")
@Builder
public class Movie {
    @Id
    private BigInteger id;
    private String title;
    private String type;
    private String description;
    private Integer year;
    private String ageCertification;
    private Integer runtime;
    private List<String> genres;
    private List<String> productionCountries;
    private Double imdbScore;

}
