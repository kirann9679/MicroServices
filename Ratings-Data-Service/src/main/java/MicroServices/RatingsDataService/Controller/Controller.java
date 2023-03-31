package MicroServices.RatingsDataService.Controller;

import MicroServices.RatingsDataService.Model.Rating;
import MicroServices.RatingsDataService.Model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/ratingsdata")
public class Controller {
    @Autowired
    UserRating userRating;
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable ("movieId") String movieId){
        return new Rating(movieId, 4.9f);
    }
    @RequestMapping("users/{userId}")
    public UserRating  getUserRating(@PathVariable ("userId") String userId){
        List<Rating> ratings= Arrays.asList(new Rating("123", 4.9f),new Rating("233",5.0f));
userRating.setUserRating(ratings);
         return userRating;
    }

}
