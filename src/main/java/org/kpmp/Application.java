package org.kpmp;

import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = { "org.kpmp" })
public class Application {

//	@Bean
//	@Profile("client")
//	HazelcastInstance hazelcastInstance() {
//		return HazelcastClient.newHazelcastClient();    // (2)
//	}
//
//	@Bean
//	CacheManager cacheManager() {
//		return new HazelcastCacheManager(hazelcastInstance()); // (3)
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/graphql").allowedOrigins("http://localhost:3000");
			}
		};
	}
}