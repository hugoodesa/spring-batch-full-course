package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Person;
import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Product;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersonProductTransacaoLineMapperConfig {

    @Bean(name = "personProductTransacaoLineMapper")
    public PatternMatchingCompositeLineMapper personProductTransacaoLineMapper() {

        PatternMatchingCompositeLineMapper<Object> lineMapper = new PatternMatchingCompositeLineMapper<>();
        Map<String, LineTokenizer> mappers = new HashMap<>();

        //PERSON
        DefaultLineMapper<Person> personMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer personTokenizer = new DelimitedLineTokenizer();
        personTokenizer.setNames("name", "age");
        personMapper.setLineTokenizer(personTokenizer);

        BeanWrapperFieldSetMapper<Person> personFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        personFieldSetMapper.setTargetType(Person.class);
        personMapper.setFieldSetMapper(personFieldSetMapper);
        personTokenizer.setIncludedFields(1, 2);


        mappers.put("1*", personTokenizer);

        //PRODUCT
        DefaultLineMapper<Product> productMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer productTokenizer = new DelimitedLineTokenizer();
        productTokenizer.setNames("name", "quantity", "price");
        productTokenizer.setIncludedFields(1, 2, 3);
        productMapper.setLineTokenizer(productTokenizer);

        BeanWrapperFieldSetMapper<Product> productFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        productFieldSetMapper.setTargetType(Product.class);
        productMapper.setFieldSetMapper(productFieldSetMapper);

        mappers.put("2*", productTokenizer);

        lineMapper.setTokenizers(mappers);
        lineMapper.setFieldSetMappers(fieldSetMappers());

        return lineMapper;
    }

    private Map<String, FieldSetMapper<Object>> fieldSetMappers(){
        Map<String, FieldSetMapper<Object>> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("1*",fieldSetMapper(Person.class));
        fieldSetMappers.put("2*",fieldSetMapper(Product.class));
        return fieldSetMappers;
    }

    private FieldSetMapper fieldSetMapper(Class clazz){
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(clazz);
        return fieldSetMapper;
    }

}
