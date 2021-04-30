package ma.youcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.youcode.shared.SpringApplicationContext;

@SpringBootApplication
public class BasmaOnlineStoreWithOutDtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasmaOnlineStoreWithOutDtoApplication.class, args);
		System.out.println("test");
	}

	// For Crypting Password
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
}
