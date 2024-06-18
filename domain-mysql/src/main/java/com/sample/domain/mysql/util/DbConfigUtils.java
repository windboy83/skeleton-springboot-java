package com.sample.domain.mysql.util;

import com.sample.domain.mysql.config.property.DbExtraConfigProperties;
import com.zaxxer.hikari.HikariConfig;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

public class DbConfigUtils {

    public static HikariConfig setExtraDBConfig(HikariConfig hikariConfig, DbExtraConfigProperties dbExtraConfigProperties) {
        if (hikariConfig == null || dbExtraConfigProperties == null) {
            return new HikariConfig();
        }

        if (dbExtraConfigProperties.getMinIdle() != null && dbExtraConfigProperties.getMinIdle() > 0) {
            hikariConfig.setMinimumIdle(dbExtraConfigProperties.getMinIdle());
        }

        if (dbExtraConfigProperties.getMaxPoolSize() != null && dbExtraConfigProperties.getMaxPoolSize() > 0) {
            hikariConfig.setMaximumPoolSize(dbExtraConfigProperties.getMaxPoolSize());
        }

        return hikariConfig;
    }

    public static EntityManagerFactory makeEntityManagerFactoryBean(DataSource dataSource, Environment env, String domainPackage, String unitName) {

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(domainPackage);

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.physical_naming_strategy",
                env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        properties.put("hibernate.format_sql",
                env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.default_batch_fetch_size",
                env.getProperty("spring.jpa.properties.hibernate.default_batch_fetch_size"));
        properties.put("hibernate.jdbc.batch_size",
                env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        properties.put("hibernate.order_inserts",
                env.getProperty("spring.jpa.properties.hibernate.jdbc.order_inserts"));
        properties.put("hibernate.order_updates",
                env.getProperty("spring.jpa.properties.hibernate.jdbc.order_updates"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));

        em.setJpaPropertyMap(properties);
        em.setPersistenceUnitName(unitName);
        em.afterPropertiesSet();

        return em.getObject();
    }
}
