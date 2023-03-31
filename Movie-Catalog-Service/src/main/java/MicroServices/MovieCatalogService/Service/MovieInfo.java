package MicroServices.MovieCatalogService.Service;

import MicroServices.MovieCatalogService.Model.CatalogItem;
import MicroServices.MovieCatalogService.Model.Movie;
import MicroServices.MovieCatalogService.Model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MovieInfo {
    @Autowired
    RestTemplate restTemplate;
   //using bulk head pattern
    //@HystrixCommand(fallbackMethod = "getFallbackUserrating",threadPoolKey = "ratingsdatapool",threadPoolProperties = {@HystrixProperty(name="coreSize",value="20"),@HystrixProperty(name="maxQueueSize",value="10")})
    //using circuit breaker pattern
    @HystrixCommand(fallbackMethod = "getFallbackgetCatalog",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")})
    public CatalogItem getCatalogItem(Rating r) {
        Movie movie = restTemplate.getForObject("http://movieinfoservice/movies/" + r.getMovieId(), Movie.class);
        return (new CatalogItem(movie.getMovieName(), movie.getDescription(), r.getRating()));
    }


    private CatalogItem getFallbackgetCatalog(Rating r) {
        return new CatalogItem("not found","",r.getRating());
    }
}
