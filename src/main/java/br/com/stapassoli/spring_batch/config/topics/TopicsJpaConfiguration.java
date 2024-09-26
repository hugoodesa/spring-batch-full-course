package br.com.stapassoli.spring_batch.config.topics;

import br.com.stapassoli.spring_batch.entity.Cliente;
import br.com.stapassoli.spring_batch.entityDois.User;
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
        basePackageClasses = Cliente.class,
        entityManagerFactoryRef = "topicsEntityManagerFactory",
        transactionManagerRef = "topicsTransactionManager"
)
public class TopicsJpaConfiguration {

    @Bean(name = "topicsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean topicsEntityManagerFactory(
            @Qualifier("topicsDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Cliente.class)
                .persistenceUnit("db2")
                .build();
    }

    @Bean(name = "topicsTransactionManager")
    public PlatformTransactionManager topicsTransactionManager(
            @Qualifier("topicsEntityManagerFactory") LocalContainerEntityManagerFactoryBean topicsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(topicsEntityManagerFactory.getObject()));
    }

}
