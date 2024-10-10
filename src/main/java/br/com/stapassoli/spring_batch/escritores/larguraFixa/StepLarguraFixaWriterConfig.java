package br.com.stapassoli.spring_batch.escritores.larguraFixa;

import br.com.stapassoli.spring_batch.dbTwo_entity.Visitors;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.io.Writer;

@Configuration
@RequiredArgsConstructor
public class StepLarguraFixaWriterConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Value("classpath:files/visitors.csv")
    Resource resource;

    @Bean(name = "stepLarguraFixaWriter")
    public Step stepLarguraFixaWriter() {
        return new StepBuilder("stepLarguraFixaWriter", jobRepository)
                .<Visitors, Visitors>chunk(10, platformTransactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

    public FlatFileItemReader<Visitors> reader() {

        return new FlatFileItemReaderBuilder<Visitors>()
                .linesToSkip(1)
                .name("visitorItemReader")
                .resource(resource)
                .delimited()
                .delimiter(",")
                .names("id", "firstName", "lastName", "emailAddress", "phoneNumber", "address")
                .targetType(Visitors.class)
                .build();
    }

    public FlatFileItemWriter<Visitors> writer() {
        WritableResource resource = new FileSystemResource("d:/DEV/batch-udemy/visitors_fixed_width.txt");

        return new FlatFileItemWriterBuilder<Visitors>()
                .name("visitorItemWriter")
                .resource(resource)
                .formatted()
                .format("%-12s %-10s %-10s %-30s %-15s %-25s")
                .names("id", "firstName", "lastName", "emailAddress", "phoneNumber", "address")
                .headerCallback(header())
                .footerCallback(footer())
                .build();
    }

    private FlatFileFooterCallback footer() {
        return writer -> writer.write("\nEnd of Visitors List");
    }

    private FlatFileHeaderCallback header() {
        return writer -> writer.write("Visitor ID    First Name    Last Name    Email Address      Phone Number    Address\n");
    }


}
