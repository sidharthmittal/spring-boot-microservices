package io.github.sidharthmittal.movieinfoservice.resources;

import io.github.sidharthmittal.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieInfo {

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId)
    {
        return new Movie(movieId, "Shawshank Redemption");
    }
}
