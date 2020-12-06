import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

 


@EnableJpaRepositories("repositories")
@ComponentScan(basePackages = { "controllers","facades","repositories","services","util","validators","configs","dto","constraint" })
@EntityScan("entidades")
@SpringBootApplication(scanBasePackages={
		"controllers","facades","repositories","services","util","validators","configs","dto","constraint" })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		 
	}



}
