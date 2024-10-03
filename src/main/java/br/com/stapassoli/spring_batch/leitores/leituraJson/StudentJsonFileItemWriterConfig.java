package br.com.stapassoli.spring_batch.leitores.leituraJson;

import br.com.stapassoli.spring_batch.leitores.leituraJson.domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class StudentJsonFileItemWriterConfig {

    @Bean(name = "studentJsonFileItem")
    public ItemWriter<Student> studentJsonFileItemWriter() {
        return students -> students.forEach(System.out::println);
    }


}
