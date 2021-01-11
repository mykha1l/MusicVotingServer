package com.haw.mvsspring;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MvsSpringApplication {

	public static void main(final String[] args) {
        SpringApplication.run(MvsSpringApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:mvsdb.db");
        return dataSourceBuilder.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/api/v1/songs").allowedOrigins("*");
                registry.addMapping("/api/v1/pairs").allowedOrigins("*");
                registry.addMapping("/api/v1/vote").allowedOrigins("*");
                registry.addMapping("/api/v1/mostlyVoted").allowedOrigins("*");
                registry.addMapping("/api/v1/upload").allowedOrigins("*");
                registry.addMapping("/api/v1/getUser").allowedOrigins("*");
                registry.addMapping("/api/v1/songs/{id}").allowedOrigins("*");
            }
        };
    }

}
