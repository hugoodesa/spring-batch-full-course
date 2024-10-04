package br.com.stapassoli.spring_batch.leitores.leituraEscritaBancoDeDados;

import br.com.stapassoli.spring_batch.dbTwo_entity.Car;
import br.com.stapassoli.spring_batch.dbTwo_entity.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StepPersistenciaDBTaskletConfig {

    private final PersistOnFromDbTasklet persistOnFromDbTasklet;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean(name = "stepPersistenciaDBTasklet")
    public Step stepPersistenciaDBTasklet () {
        return new StepBuilder("stepPersistenciaDBTasklet", jobRepository)
                .tasklet(persistOnFromDbTasklet,transactionManager)
                .build();
    }

}

@RequiredArgsConstructor
@Component
class PersistOnFromDbTasklet implements Tasklet {
    private final CarRepository carRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        /*List<Car> cars = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            cars.add(Car.builder().nome(String.format("Car %d", i)).build());
        }

        carRepository.saveAll(cars);*/
        return RepeatStatus.FINISHED;
    }

}