package io.github.sidharthmittal.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.sidharthmittal.moviecatalogservice.models.CatalogItem;
import io.github.sidharthmittal.moviecatalogservice.models.Movie;
import io.github.sidharthmittal.moviecatalogservice.models.Rating;
import io.github.sidharthmittal.moviecatalogservice.models.UserRating;
import io.github.sidharthmittal.moviecatalogservice.services.MovieInfo;
import io.github.sidharthmittal.moviecatalogservice.services.RatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    RatingInfo ratingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        UserRating ratings = ratingInfo.getUserRating(userId);

        return ratings.getUserRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
/*
            //using webclient
            Movie movie = builder.build()
                    .get()
                    .uri("http://localhost:8082/movie/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

 */
}
