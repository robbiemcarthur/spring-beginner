package com.pluralsight.conference.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// TODO: Look up auto configuration files, similar to c# class libs - JARs with helper modules etc
@Configuration
public class PersistenceConfiguration {

//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.url("jdbc:postgresql://localhost:5432/conference_app");
//        builder.username("postgres");
//        builder.password("Welcome");
//        System.out.println("Custom data source bean initialized and set");
//        return builder.build();
//    }
}
