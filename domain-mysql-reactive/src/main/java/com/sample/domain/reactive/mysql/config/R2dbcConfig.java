package com.sample.domain.reactive.mysql.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories(basePackages = "com.sample.domain.reactive.mysql.repository")
public class R2dbcConfig {
}
