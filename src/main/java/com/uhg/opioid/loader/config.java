package com.uhg.opioid.loader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class config {
    @Bean(name = "db1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate myTemplate(@Qualifier("db1") DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate myTemplate2(@Qualifier("db2") DataSource ds){
        return new JdbcTemplate(ds);
    }
}

