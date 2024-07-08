package com.sample.domain.mongodb.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.bson.UuidRepresentation;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "spring.data.mongodb")
@EnableConfigurationProperties
@Getter
@Setter
@Component
public class MongoDbProperties {
    private String host;
    private Integer port;
    private List<String> additionalHosts;
    private String uri;
    private String database;
    private String authenticationDatabase;
    private MongoProperties.Gridfs gridfs;
    private String username;
    private String password;
    private String replicaSetName;
    private Class<?> fieldNamingStrategy;
    private UuidRepresentation uuidRepresentation = UuidRepresentation.STANDARD;
    private MongoProperties.Ssl ssl;
    private Boolean autoIndexCreation;
    private Integer minSize;
    private Integer maxSize;
    private Integer maxConnecting;
    private Integer maxConnectionLifeTime;
    private Integer connectTimeout;
    private Integer readTimeout;

}
