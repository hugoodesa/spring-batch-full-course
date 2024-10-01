package br.com.stapassoli.spring_batch.leitores.delimitadoCSV;

import br.com.stapassoli.spring_batch.dbTwo_entity.Visitors;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelimitadoLineMapperConfig {


    @Bean
    public LineMapper<Visitors> delimitadorLineMapper() {
        DefaultLineMapper<Visitors> visitorsDefaultLineMapper = new DefaultLineMapper<>();
        visitorsDefaultLineMapper.setLineTokenizer(lineTokenizerVisitor());
        visitorsDefaultLineMapper.setFieldSetMapper(fieldSetMapperVisitor());

        return visitorsDefaultLineMapper;
    }

    private DelimitedLineTokenizer lineTokenizerVisitor() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("id", "firstName", "lastName", "emailAddress", "phoneNumber", "address", "strVisitDate");

        return delimitedLineTokenizer;
    }

    private BeanWrapperFieldSetMapper<Visitors> fieldSetMapperVisitor() {
        BeanWrapperFieldSetMapper<Visitors> wrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        wrapperFieldSetMapper.setTargetType(Visitors.class);
        return wrapperFieldSetMapper;
    }

}
