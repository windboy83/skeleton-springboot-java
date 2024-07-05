package com.sample.domain.reactive.mysql.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "extra.config.db.mysql")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class DbExtraConfigProperties {

    private Integer initialSize;
    private Integer maxSize;
}
