package MicroServices.RatingsDataService;

import MicroServices.RatingsDataService.Model.UserRating;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RatingsDataServiceApplication {
	@Bean
	public UserRating getUserRating(){
		return new UserRating();
	}

	public static void main(String[] args) {
		SpringApplication.run(RatingsDataServiceApplication.class, args);
	}

}
//