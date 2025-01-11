package org.hyeonqz.week3lotto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.jackson.ModelResolver;

@Configuration
public class SwaggerConfig {

	// 접속 URL: http://localhost:8081/swagger-ui.html

	@Bean
	public ModelResolver modelResolver(ObjectMapper objectMapper){
		return new ModelResolver(objectMapper);
	}
}