package com.project.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class CodingChallengeApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:application-aws.yml";


	public static void main(String[] args) {
		new SpringApplicationBuilder(CodingChallengeApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
