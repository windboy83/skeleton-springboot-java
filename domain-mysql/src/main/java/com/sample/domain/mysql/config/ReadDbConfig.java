package com.sample.domain.mysql.config;

import com.sample.domain.mysql.config.property.DbExtraConfigProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static com.sample.domain.mysql.util.DbConfigUtils.makeEntityManagerFactoryBean;
import static com.sample.domain.mysql.util.DbConfigUtils.setExtraDBConfig;

@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = {"com.sample.domain.mysql.read.repository"},
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)
@Configuration
public class ReadDbConfig {

    private final Environment env;
    private final DbExtraConfigProperties dbExtraConfigProperties;

    private static final String DATASOURCE_BEAN_NAME = "readDataSource";
    private static final String HIKARY_CONFIG_BEAN_NAME = "readHikaryConfig";
    private static final String ENTITY_MANAGER_BEAN_NAME = "readEntityManagerFactory";
    private static final String TRANSACTION_MANAGER_BEAN_NAME = "readTransactionManager";

    @Bean(name = DATASOURCE_BEAN_NAME)
    public DataSource dataSource(@Qualifier(HIKARY_CONFIG_BEAN_NAME) HikariConfig hikariConfig) {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(setExtraDBConfig(hikariConfig, dbExtraConfigProperties)));
    }

    @Bean(name = HIKARY_CONFIG_BEAN_NAME)
    @ConfigurationProperties(prefix = "db-config.read.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
    public EntityManagerFactory entityManagerFactory(@Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource) {
        return makeEntityManagerFactoryBean(dataSource, env, "com.sample.domain.mysql.domain", "read");
    }

    @Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_BEAN_NAME) EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
