package MicroServices.MovieCatalogService.Controller;

import MicroServices.MovieCatalogService.Model.CatalogItem;
import MicroServices.MovieCatalogService.Model.Movie;
import MicroServices.MovieCatalogService.Model.Rating;
import MicroServices.MovieCatalogService.Model.UserRating;
import MicroServices.MovieCatalogService.Service.MovieInfo;
import MicroServices.MovieCatalogService.Service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/catalog")
public class Controller {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;
    Log4j logger;
    @Autowired
    MovieInfo movieInfo;
    @Autowired
    UserRatingInfo userRatingInfo;
    @Autowired
    WebClient web;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getcatalog(@PathVariable("userId") String userId) {

        UserRating ratings = userRatingInfo.getUserRating(userId);

        List<CatalogItem> response = ratings.getUserRating().stream().map(r -> movieInfo.getCatalogItem(r))
                .collect(Collectors.toList());



        return response;
    }
}


//using webclient instead of resttemplate
// Movie movie=  webClient.get().uri("http://localhost:8082/movies/" + r.getMovieId()).retrieve().bodyToMono(Movie.class).block();



