package com.netflix.controller;

import com.netflix.dto.NetflixMovieRequest;
import com.netflix.dto.NetflixMovieResponse;
import com.netflix.service.NetflixMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NetflixMovieController {
    private NetflixMovieService netflixMovieService;

    public NetflixMovieController(NetflixMovieService netflixMovieService){
        this.netflixMovieService = netflixMovieService;
    }

    @PostMapping
    public NetflixMovieResponse create(@RequestBody NetflixMovieRequest netflixMovieRequest) {
        return netflixMovieService.createMovie(netflixMovieRequest);
    }

    @PatchMapping("/{title}")
    public NetflixMovieResponse update(@PathVariable(value = "title") String title, @RequestBody NetflixMovieRequest netflixMovieRequest){
        return netflixMovieService.updateMovieDetails(title, netflixMovieRequest);
    }

    @GetMapping
    public List<NetflixMovieResponse> getAllMovies(){
        return netflixMovieService.getAllMovies();
    }

    @GetMapping("/{title}")
    public NetflixMovieResponse getMovies(@PathVariable(value = "title") String title){
        return netflixMovieService.getMovie(title);
    }

}
