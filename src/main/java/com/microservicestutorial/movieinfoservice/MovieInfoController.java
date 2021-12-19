package com.microservicestutorial.movieinfoservice;

import com.microservicestutorial.movieinfoservice.config.DbConnectionProperties;
import com.microservicestutorial.movieinfoservice.resources.MovieInfoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${movie-db.api-key}")
    private String apiKey;

    @Value("${my-default-value-variable:defaultValue}")
    private String defaultValue;

    @Value("${my-list-values-variable}")
    private List<String> values;

    @Value("#{${my-map-values-variable}}") //the # tells that this is an SpEL expression
    private Map<String, String> mapValues;

    @Autowired
    private Environment env;

    @Autowired
    DbConnectionProperties dbConnectionProperties;

    @GetMapping("/{id}")
    public MovieInfoResource getMovieById(@PathVariable(value = "id") int movieId) {
        System.out.println("default value : " + defaultValue);
        System.out.println("values : " + values);
        System.out.println("mapValues : " + mapValues);
        System.out.println("db connection settings :  " + dbConnectionProperties);
        return restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieInfoResource.class);
    }

    @GetMapping("/env")
    public String getEnvironnement() {
        return env.toString();
    }
}
