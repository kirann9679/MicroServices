package MicroServices.MovieCatalogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableHystrix
//@EnableHystrixDashboard
@SpringBootApplication
public class MovieCatalogServiceApplication {
	@Bean
	@LoadBalanced
	public RestTemplate gettemplate() {
		//setting the timeout
		//HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
		//httpComponentsClientHttpRequestFactory.setConnectTimeout(5000);
		//return new RestTemplate(httpComponentsClientHttpRequestFactory);
		return new RestTemplate();
	}
	@Bean
public WebClient getWebClient(){
		return WebClient.builder().build();
}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);

	}
}