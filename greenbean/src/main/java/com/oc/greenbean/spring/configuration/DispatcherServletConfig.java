package com.oc.greenbean.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.oc",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..controller..*"),
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.oc..view..*")
        })
public class DispatcherServletConfig {

}
