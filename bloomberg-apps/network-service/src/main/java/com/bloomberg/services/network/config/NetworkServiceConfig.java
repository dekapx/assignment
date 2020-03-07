package com.bloomberg.services.network.config;

import com.bloomberg.services.network.command.Command;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NetworkServiceConfig {
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ServiceLocatorFactoryBean CommandFactoryServiceLocatorBean() {
        final ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(Command.class);
        return factoryBean;
    }
}
