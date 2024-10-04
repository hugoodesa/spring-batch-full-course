package br.com.stapassoli.spring_batch.leitores.leituraEscritaBancoDeDados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Car;
import br.com.stapassoli.spring_batch.dbTwo_entity.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.List;

//IMPORTANT CONCEPT
/*If the chunk size is smaller than the page size, then Spring Batch will process part of the page,
but will not re-fetch the page until the entire chunk has been processed.
This leads to fewer database queries, but you might process partial chunks.*/

/*If the chunk size is larger than the page size,
Spring Batch will re-fetch the database multiple times to fulfill the chunk size.
This can result in multiple queries for each chunk, leading to higher overhead.*/

@Configuration
@RequiredArgsConstructor
public class StepLeituraDBChunkConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final CarRepository carRepository;

    @Bean("stepLeituraDB")
    public Step stepLeituraDB() {
        return new StepBuilder("stepLeituraDB", jobRepository)
                .<Car, Car>chunk(4, platformTransactionManager)
                .reader(leituraPaginada())
                .writer(printCars())
                .build();
    }

    private ItemReader<Car> getAllCarsInDataBase() {
        List<Car> allCars = carRepository.findAll();
        return new IteratorItemReader<>(allCars);
    }

    private ItemWriter<Car> printCars() {
        return System.out::println;
    }

    //leitura paginada
    public ItemReader<Car> leituraPaginada() {
        HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);

        return new RepositoryItemReaderBuilder<Car>()
                .repository(carRepository)
                .name("leituraPaginada")
                .methodName("findAll")
                .pageSize(4)
                .sorts(sorts)
                .build();

    }


}
