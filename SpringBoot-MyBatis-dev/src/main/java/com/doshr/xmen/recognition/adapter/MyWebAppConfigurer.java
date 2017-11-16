package com.doshr.xmen.recognition.adapter;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.doshr.xmen.recognition.adapter.interceptor.AuthInterceptor;

/**
 * 
 * @author leon
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcAutoConfigurationAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/hello","/users/**","/api-docs/**");
		super.addInterceptors(registry);
	}
}
