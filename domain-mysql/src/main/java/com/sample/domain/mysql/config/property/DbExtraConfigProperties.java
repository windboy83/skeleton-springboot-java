package com.sample.domain.mysql.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mysql.db.extra.config")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class DbExtraConfigProperties {

    private Integer minIdle;
    private Integer maxPoolSize;
}
