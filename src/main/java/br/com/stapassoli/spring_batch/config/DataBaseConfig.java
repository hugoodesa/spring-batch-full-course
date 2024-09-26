package br.com.stapassoli.spring_batch.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    //#atodo data source
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.todos")
    public DataSourceProperties todosDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.todos")
    public DataSource todosDataSource() {
        return todosDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    //#b topics data source
    @Bean(name = "topicsDataSourceProperties")
    @ConfigurationProperties("spring.datasource.app")
    public DataSourceProperties topicsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "topicsDataSource")
    @ConfigurationProperties("spring.datasource.app")
    public DataSource topicsDataSource() {
        return topicsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
