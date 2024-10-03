package br.com.stapassoli.spring_batch.leitores.leituraEscritaBancoDeDados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Car;
import br.com.stapassoli.spring_batch.dbTwo_entity.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepLeituraDBConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final CarRepository carRepository;

    @Bean("stepLeituraDB")
    public Step stepLeituraDB() {
        return new StepBuilder("stepLeituraDB", jobRepository)
                .<Car, Car>chunk(1, platformTransactionManager)
                .reader(getAllCarsInDataBase())
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

}
