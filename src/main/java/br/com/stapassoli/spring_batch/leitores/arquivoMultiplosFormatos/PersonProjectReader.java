package br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos;

import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Person;
import br.com.stapassoli.spring_batch.leitores.arquivoMultiplosFormatos.domain.Product;
import org.springframework.batch.item.*;

import java.util.Objects;

public class PersonProjectReader implements ItemStreamReader<Person> {

    private Object actual;
    private ItemStreamReader<Object> delegate;

    public PersonProjectReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Person read() throws Exception {

        if (Objects.isNull(actual)) {
            this.actual = delegate.read();
        }

        Person person = (Person) this.actual;
        this.actual = null;

        if (Objects.nonNull(person)) {
            while (peek() instanceof Product) {
                Product product = (Product) this.actual;
                person.addProductToList(product);
            }
        }

        return person;
    }

    private Object peek() throws Exception {
        this.actual = this.delegate.read();
        return this.actual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
