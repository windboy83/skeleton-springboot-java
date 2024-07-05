package com.sample.domain.reactive.mongodb.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "extra.config.db.mongo")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class MongoDbExtraConfigProperties {

    private Integer minSize;
    private Integer maxSize;
}
