package br.com.stapassoli.spring_batch.leitores.lerDeVariasFontes;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerVariasFontesWriterConfig {

    @Bean
    public ItemWriter<Employee> lerVariasFontesWriter() {
        return employs -> employs.forEach(System.out::println);
    }

}
