package br.com.stapassoli.spring_batch.processadores;

import br.com.stapassoli.spring_batch.processadores.domain.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorValidacaoConfig {

    Set<String> emails = new HashSet<>();

    @Bean(name = "compositeItemProcessor")
    public ItemProcessor<Cliente, Cliente> compositeItemProcessor() throws Exception {
        return new CompositeItemProcessorBuilder<Cliente, Cliente>()
                .delegates(processadorValidacaoCustomizado(), processadorValidacao()).build();
    }

    public ItemProcessor<Cliente, Cliente> processadorValidacao() throws Exception {
        BeanValidatingItemProcessor<Cliente> processador = new BeanValidatingItemProcessor<>();
        processador.setFilter(true);
        processador.afterPropertiesSet();
        return processador;
    }

    public ItemProcessor<Cliente, Cliente> processadorValidacaoCustomizado() throws Exception {
        ValidatingItemProcessor<Cliente> clienteValidator = new ValidatingItemProcessor<Cliente>();
        clienteValidator.setValidator(myCustomValidator());
        clienteValidator.setFilter(true);
        clienteValidator.afterPropertiesSet();
        return clienteValidator;
    }

    private Validator<Cliente> myCustomValidator() {
        return cliente -> {
            if (emails.contains(cliente.getEmail())) {
                throw new ValidationException(String.format("O cliente %s já foi processado", cliente.getNome()));
            }
            emails.add(cliente.getEmail());
        };
    }

}
