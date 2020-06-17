@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DirectionsappApplication {
	@GetMapping(value="/")
	public String home() {

		return "home page";
	}
	public static void main(String[] args) {
		SpringApplication.run(DirectionsappApplication.class, args);
	}

}