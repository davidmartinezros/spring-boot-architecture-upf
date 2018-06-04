package edu.upf.model.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@ComponentScan(basePackages = { "edu.upf.model.controller" })
public class WebConfig implements WebMvcConfigurer {
 
  @Bean
  public SimpleMappingExceptionResolver exceptionResolver() {
      SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
 
      exceptionResolver.setExceptionMappings(exceptionMappingsProperties());
      exceptionResolver.setDefaultErrorView("/error/500");
      return exceptionResolver;
   }
 
   private Properties exceptionMappingsProperties() {
      Properties exceptionMapping = new Properties();
 
      exceptionMapping.put("java.lang.Exception", "error/500");
      exceptionMapping.put("java.lang.RuntimeException", "error/500");
      return exceptionMapping;
 
   }
}