package br.com.stapassoli.spring_batch.config.todo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //basePackageClasses = MyUser.class,
        basePackages = "br.com.stapassoli.spring_batch.dbOne_entity",
        entityManagerFactoryRef = "todosEntityManagerFactory",
        transactionManagerRef = "todosTransactionManager"
)
public class TodoJpaConfiguration {

    @Primary
    @Bean(name = "todosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
            @Qualifier("todosDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.stapassoli.spring_batch.dbOne_entity")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "todosTransactionManager")
    public PlatformTransactionManager todosTransactionManager(
            @Qualifier("todosEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }

}
