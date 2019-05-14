package br.com.bv.juliocesar.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.bv.juliocesar.controller.MensagemController;

@EnableWebMvc
@ComponentScan(basePackageClasses={MensagemController.class})
public class AppConfig {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		return resolver;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
		
		return restTemplate;
	}
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonResourceViewResolver());
		
		ContentNegotiatingViewResolver contentResolver = new ContentNegotiatingViewResolver();
		contentResolver.setViewResolvers(viewResolvers);
		contentResolver.setContentNegotiationManager(manager);
		
		return contentResolver;
	}
	
}
