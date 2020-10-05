package io.github.sidharthmittal.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.sidharthmittal.moviecatalogservice.models.CatalogItem;
import io.github.sidharthmittal.moviecatalogservice.models.Movie;
import io.github.sidharthmittal.moviecatalogservice.models.Rating;
import io.github.sidharthmittal.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackGetUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
    }

    public UserRating getFallbackGetUserRating(String userId)
    {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRatings(
                Arrays.asList( new Rating("0",0))
        );

        return userRating;
    }
}
