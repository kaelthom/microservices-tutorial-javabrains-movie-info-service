package com.microservicestutorial.movieinfoservice;

import com.microservicestutorial.movieinfoservice.resources.MovieInfoResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @GetMapping("/{id}")
    public MovieInfoResource getMovieById(@PathVariable(value = "id") int id) {
        return new MovieInfoResource(id, "Minority Report", "sci fi film with TomCruise");
    }
}
