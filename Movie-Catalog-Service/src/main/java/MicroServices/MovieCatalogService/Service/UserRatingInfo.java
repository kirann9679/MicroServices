package MicroServices.MovieCatalogService.Service;

import MicroServices.MovieCatalogService.Model.Rating;
import MicroServices.MovieCatalogService.Model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@Service
public class UserRatingInfo {
    @Autowired
    RestTemplate restTemplate;
    //using bulk head pattern
   //@HystrixCommand(fallbackMethod = "getFallbackUserrating",threadPoolKey = "ratingsdatapool",threadPoolProperties = {@HystrixProperty(name="coreSize",value="20"),@HystrixProperty(name="maxQueueSize",value="10")})

    //using circuit breaker pattern
    @HystrixCommand(fallbackMethod = "getFallbackUserrating",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
                                                                                  @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
                                                                                  @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
                                                                                  @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")})
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
    }


    private UserRating getFallbackUserrating(String userId) {
        UserRating userRating=new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(new Rating("1",0.0f),new Rating("2",2.3f)));
        return userRating;
    }
}
