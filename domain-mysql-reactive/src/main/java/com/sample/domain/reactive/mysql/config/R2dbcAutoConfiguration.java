package com.sample.domain.reactive.mysql.config;


import com.sample.domain.reactive.mysql.config.property.DbConfigProperties;
import com.sample.domain.reactive.mysql.config.property.DbExtraConfigProperties;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class R2dbcAutoConfiguration extends AbstractR2dbcConfiguration {
    private final DbConfigProperties dbConfigProperties;
    private final DbExtraConfigProperties dbExtraConfigProperties;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.SSL, dbConfigProperties.getSsl())
                .option(ConnectionFactoryOptions.DRIVER, dbConfigProperties.getDriver())
                .option(ConnectionFactoryOptions.PROTOCOL, dbConfigProperties.getProtocol())
                .option(ConnectionFactoryOptions.HOST, dbConfigProperties.getHost())
                .option(ConnectionFactoryOptions.PORT, dbConfigProperties.getPort())
                .option(ConnectionFactoryOptions.USER, dbConfigProperties.getUser())
                .option(ConnectionFactoryOptions.PASSWORD, dbConfigProperties.getPassword())
                .option(ConnectionFactoryOptions.DATABASE, dbConfigProperties.getDatabase())
                .option(Option.valueOf("initialSize"), dbExtraConfigProperties.getInitialSize() == null ? dbConfigProperties.getInitialSize() : dbExtraConfigProperties.getInitialSize())
                .option(Option.valueOf("maxSize"), dbExtraConfigProperties.getMaxSize() == null ? dbConfigProperties.getMaxSize() : dbExtraConfigProperties.getMaxSize())
                .option(Option.valueOf("validationQuery"), dbConfigProperties.getValidationQuery())
                .option(Option.valueOf("serverZoneId"), ZoneId.systemDefault())
                .option(Option.valueOf("characterEncoding"), dbConfigProperties.getCharacterEncoding())
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
