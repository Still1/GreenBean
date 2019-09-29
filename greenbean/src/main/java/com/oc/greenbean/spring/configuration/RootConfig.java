package com.oc.greenbean.spring.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.oc",
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..service..*"),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..component..*"),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..repository..*")
    })
public class RootConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/greenbean");
        dataSource.setUsername("root");
        dataSource.setPassword("mysqlroot");
        return dataSource;
    }
}
