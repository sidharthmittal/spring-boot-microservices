package io.github.sidharthmittal.ratingsdataservice.resources;

import io.github.sidharthmittal.ratingsdataservice.models.Rating;
import io.github.sidharthmittal.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsData {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)
    {
        return new Rating(movieId,4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId)
    {
        List<Rating> ratings = Arrays.asList(
                new Rating("500", 4),
                new Rating("900", 3)
        );
        UserRating userRating = new UserRating(ratings);
        return userRating;
    }

}
