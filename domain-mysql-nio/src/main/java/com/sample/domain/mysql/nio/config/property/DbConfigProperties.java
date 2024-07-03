package com.sample.domain.mysql.nio.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.r2dbc")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class DbConfigProperties {

    private String url;
    private String username;
    private String password;
    private DbPoolProperties dbPoolProperties;

    @Getter
    @Setter
    public static class DbPoolProperties {
        private Integer minIdle;
        private Integer maxSize;
        private String validationQuery;
    }
}
