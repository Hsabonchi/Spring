package spRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpRestApplication.class, args);
	}

}
