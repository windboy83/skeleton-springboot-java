package com.sample.domain.mongodb.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.sample.domain.mongodb.config.properties.MongoDbExtraConfigProperties;
import com.sample.domain.mongodb.config.properties.MongoDbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.mongo.MongoConnectionDetails;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackages = "com.sample.domain.mongodb.repository")
@RequiredArgsConstructor
public class MongoDBConfig {
    private final MongoDbProperties mongoDbProperties;
    private final MongoDbExtraConfigProperties mongoDbExtraConfigProperties;

    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate(MongoClient mongoClient) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDbProperties.getDatabase());
        return mongoTemplate;
    }

    @Bean(name = "mongoClient")
    public MongoClient getMongoClient(MongoClientSettings mongoClientSettings) {
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient;
    }

    @Bean(name = "mongoClientSettings")
    public MongoClientSettings mongoClientSettings(
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

    @Bean(name = "mongoTransactionManager")
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
