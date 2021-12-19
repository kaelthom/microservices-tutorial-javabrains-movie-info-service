package com.microservicestutorial.movieinfoservice;

import com.microservicestutorial.movieinfoservice.resources.MovieInfoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${movie-db.api-key}")
    private String apiKey;

    @GetMapping("/{id}")
    public MovieInfoResource getMovieById(@PathVariable(value = "id") int movieId) {
        return restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieInfoResource.class);
    }
}
