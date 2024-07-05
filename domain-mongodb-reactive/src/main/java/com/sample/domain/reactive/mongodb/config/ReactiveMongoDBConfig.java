package com.sample.domain.reactive.mongodb.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.sample.domain.reactive.mongodb.config.properties.MongoDbExtraConfigProperties;
import com.sample.domain.reactive.mongodb.config.properties.MongoDbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.mongo.MongoConnectionDetails;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.sample.domain.reactive.mongodb.repository")
@RequiredArgsConstructor
public class ReactiveMongoDBConfig {
    private final MongoDbProperties mongoDbProperties;
    private final MongoDbExtraConfigProperties mongoDbExtraConfigProperties;

    @Bean(name = "reactiveMongoTemplate")
    ReactiveMongoTemplate getReactiveMongoTemplate(MongoClient mongoClient) {
        ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(mongoClient, mongoDbProperties.getDatabase());
        return mongoTemplate;
    }

    @Bean(name = "mongoClient")
    MongoClient getMongoClient(MongoClientSettings mongoClientSettings) {
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient;
    }

    @Bean(name = "mongoClientSettings")
    MongoClientSettings mongoClientSettings(
            MongoConnectionDetails connectionDetails,
            ObjectProvider<SslBundles> sslBundlesProvider) {

        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .applyConnectionString(connectionDetails.getConnectionString())
                .uuidRepresentation(mongoDbProperties.getUuidRepresentation())
                .applyToConnectionPoolSettings(connectionPoolSettingsBuilder -> connectionPoolSettingsBuilder
                        .maxSize(mongoDbExtraConfigProperties.getMaxSize() == null ? mongoDbProperties.getMaxSize() : mongoDbExtraConfigProperties.getMaxSize())
                        .minSize(mongoDbExtraConfigProperties.getMinSize() == null ? mongoDbProperties.getMinSize() : mongoDbExtraConfigProperties.getMinSize())
                        .maxConnecting(mongoDbProperties.getMaxConnecting())
                        .maxConnectionLifeTime(mongoDbProperties.getMaxConnectionLifeTime(), TimeUnit.MINUTES)
                )
                .applyToSocketSettings(socketSetting ->
                        socketSetting
                                .connectTimeout(mongoDbProperties.getConnectTimeout(), TimeUnit.SECONDS)
                                .readTimeout(mongoDbProperties.getReadTimeout(), TimeUnit.SECONDS)
                )
                .readPreference(ReadPreference.secondaryPreferred())
                .retryWrites(false);

        if (mongoDbProperties.getSsl().isEnabled()) {
            builder.applyToSslSettings(sslSetting -> {
                if (mongoDbProperties.getSsl().getBundle() != null) {
                    sslSetting.enabled(true);
                    SslBundles sslBundles = sslBundlesProvider.getIfAvailable();
                    SslBundle sslBundle = sslBundles.getBundle(mongoDbProperties.getSsl().getBundle());
                    Assert.state(!sslBundle.getOptions().isSpecified(), "SSL options cannot be specified with MongoDB");
                    sslSetting.context(sslBundle.createSslContext());
                }
            });
        }

        return builder.build();
    }

    @Bean
    ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbFactory) {
        return new ReactiveMongoTransactionManager(dbFactory);
    }
}
