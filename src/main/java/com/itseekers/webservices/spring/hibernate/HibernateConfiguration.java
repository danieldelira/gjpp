/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.spring.hibernate;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author cheve360
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:com/itseekers/webservices/properties/hibernate.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public org.springframework.orm.hibernate4.LocalSessionFactoryBean sessionFactory() {
        org.springframework.orm.hibernate4.LocalSessionFactoryBean sessionFactory = new org.springframework.orm.hibernate4.LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.itseekers.webservices.dto"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public javax.sql.DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));  
        
        properties.put("hibernate.connection.CharSet", environment.getRequiredProperty("hibernate.connection.CharSet"));  
        properties.put("hibernate.connection.characterEncoding", environment.getRequiredProperty("hibernate.connection.characterEncoding"));  
        properties.put("hibernate.connection.useUnicode", environment.getRequiredProperty("hibernate.connection.useUnicode"));  
        
        return properties;
    }

    @Bean
    @Autowired
    public org.springframework.orm.hibernate4.HibernateTransactionManager transactionManager(SessionFactory s) {
        org.springframework.orm.hibernate4.HibernateTransactionManager txManager = new org.springframework.orm.hibernate4.HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
