package com.sample.domain.reactive.mysql.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.sample.r2dbc")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class DbConfigProperties {
    private Boolean ssl;
    private String driver;
    private String protocol;
    private String host;
    private Integer port;
    private String user;
    private String password;
    private String database;
    private Integer initialSize;
    private Integer maxSize;
    private String validationQuery;
    private String characterEncoding;
}
