package br.com.stapassoli.spring_batch.leitores.leituraJson;

import br.com.stapassoli.spring_batch.leitores.leituraJson.domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class StudentJsonItemReaderConfig {

    @Value("classpath:files/students.json")
    private Resource resource;

    ObjectMapper mapper;
    JacksonJsonObjectReader<Student> jsonObjectReader;

    public StudentJsonItemReaderConfig() {
        this.mapper = new ObjectMapper();
        this.jsonObjectReader = new JacksonJsonObjectReader<>(mapper, Student.class);
    }

    @Bean(name = "studentJsonItemReader")
    public JsonItemReader<Student> studentJsonItemReader() {
        return new JsonItemReaderBuilder<Student>()
            .name("studentJsonItemReader")
            .strict(false)
            .resource(resource)
            .jsonObjectReader(jsonObjectReader)
            .build();

    }


}
