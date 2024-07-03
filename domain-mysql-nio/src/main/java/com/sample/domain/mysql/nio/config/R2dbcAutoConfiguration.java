package com.sample.domain.mysql.nio.config;


import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.time.ZoneId;

@Configuration
@AutoConfiguration(before = TransactionAutoConfiguration.class)
@ConditionalOnClass({R2dbcTransactionManager.class, ReactiveTransactionManager.class})
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class R2dbcAutoConfiguration extends AbstractR2dbcConfiguration {

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.SSL, true)
                .option(ConnectionFactoryOptions.DRIVER, "pool")
                .option(ConnectionFactoryOptions.PROTOCOL, "mysql")
                .option(ConnectionFactoryOptions.HOST, "localhost")
                .option(ConnectionFactoryOptions.PORT, 3306)
                .option(ConnectionFactoryOptions.USER, "sample")
                .option(ConnectionFactoryOptions.PASSWORD, "sample1234!@")
                .option(ConnectionFactoryOptions.DATABASE, "sample")
                .option(Option.valueOf("initialSize"), 5)
                .option(Option.valueOf("maxSize"), 20)
                .option(Option.valueOf("validationQuery"), "select 1")
                .option(Option.valueOf("serverZoneId"), ZoneId.systemDefault())
                .option(Option.valueOf("characterEncoding"), "UTF-8")
                .build());

        return connectionFactory;
    }

    @Bean
    @ConditionalOnSingleCandidate(ConnectionFactory.class)
    @ConditionalOnMissingBean(ReactiveTransactionManager.class)
    public R2dbcTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
