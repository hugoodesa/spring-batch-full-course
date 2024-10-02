package br.com.stapassoli.spring_batch.leitores.lerDeVariasFontes;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LerVariasFontesReaderConfig {

    @Value("classpath:files/inputData*.csv")
    private Resource[] inputResources;

    @Bean(name = "lerVariasFontesReader")
    public MultiResourceItemReader<Employee> lerVariasFontesReader(FlatFileItemReader<Employee> lerVariasFontesReaderFlatItemReader) {
        return new MultiResourceItemReaderBuilder<Employee>()
                .name("lerVariasFontesReader")
                .resources(inputResources)
                .delegate(lerVariasFontesReaderFlatItemReader)
                .build();
    }

    @Bean(name = "lerVariasFontesReaderFlatItemReader")
    public FlatFileItemReader<Employee> lerVariasFontesReaderFlatItemReader(LineMapper<Employee> lerVariasFontesLineMapper) {
        return new FlatFileItemReaderBuilder<Employee>()
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names("id", "firstName", "lastName")
                .name("lerVariasFontesReader")
                .targetType(Employee.class)
                .lineMapper(lerVariasFontesLineMapper)
                .fieldSetMapper(fieldSetMapperEmployee())
                .build();
    }

    @Bean
    public LineMapper<Employee> lerVariasFontesLineMapper() {
        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizerEmployee());
        defaultLineMapper.setFieldSetMapper(fieldSetMapperEmployee());

        return defaultLineMapper;
    }

    private BeanWrapperFieldSetMapper<Employee> fieldSetMapperEmployee() {
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);
        return fieldSetMapper;
    }

    private LineTokenizer lineTokenizerEmployee() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("id", "firstName", "lastName");
        return delimitedLineTokenizer;
    }

}
