package com.sergei.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.sergei.spring.rest")
@EnableWebMvc
public class MyConfig {

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://172.17.0.2:3306/my_db?useSSl=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("admin");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        sessionFactoryBean().setDataSource(dataSource());
        sessionFactoryBean().setPackagesToScan("com.sergei.spring.rest.entity");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql",
                "true");
        sessionFactoryBean().setHibernateProperties(hibernateProperties);
        return sessionFactoryBean();

    }
}
