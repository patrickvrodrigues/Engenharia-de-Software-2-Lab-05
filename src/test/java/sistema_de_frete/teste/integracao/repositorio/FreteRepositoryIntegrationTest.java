package sistema_de_frete.teste.integracao.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sistema_de_frete.builder.CidadeBuilder;
import sistema_de_frete.builder.ClienteBuilder;
import sistema_de_frete.builder.FreteBuilder;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.repositorio.CidadeRepository;
import sistema_de_frete.repositorio.ClienteRepository;
import sistema_de_frete.repositorio.FreteRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FreteRepositoryIntegrationTest {

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;


    private Cliente cliente;
    private Cidade cidade;
    private Frete frete;
    private Cliente cliente1;
    private Cidade cidade1;
    private Frete frete1;
    private Frete frete2;
    private Cidade cidade2;

    @BeforeEach
    public void before() {
        //Construção do Cenário
        cliente = ClienteBuilder.umCliente().constroi();
        cliente = clienteRepository.save(cliente);
        cidade = CidadeBuilder.umaCidade().constroi();
        cidade = cidadeRepository.save(cidade);
        frete = FreteBuilder.umFrete().comCidade(cidade).comCliente(cliente).constroi();
        frete.setValor(1000f);

        cliente1 = ClienteBuilder.umCliente().comNome("Fernando").constroi();
        cliente1 = clienteRepository.save(cliente1);
        cidade1 = CidadeBuilder.umaCidade().comNome("São Luis").constroi();
        cidade1 = cidadeRepository.save(cidade1);
        frete1 = FreteBuilder.umFrete().comCliente(cliente1).comCidade(cidade1).constroi();
        frete1.setValor(1000f);

        cidade2 = CidadeBuilder.umaCidade().comNome("Belém").constroi();
        cidade2 = cidadeRepository.save(cidade2);
        frete2 = FreteBuilder.umFrete().comCidade(cidade2).comCliente(cliente).constroi();
        frete2.setValor(2000f);

    }


    @Test
    public void saveComClienteNuloDeveLancarException() throws Exception {

        //Teste do Repositório
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {  frete.setCliente(null);
                    freteRepository.save(frete);
                },
                "Deveria lançar um ConstraintViolationException");
        //Verificação
        Assertions.assertTrue(exception.getMessage().contains("Deve conter um Cliente"));
    }

    @Test
    public void saveComCidadeNulaDeveLancarException() throws Exception {
        //Teste do Repositório
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> {  frete.setCidade(null);
                    freteRepository.save(frete);
                },
                "Deveria lançar um ConstraintViolationException");
        //Verificação
        Assertions.assertTrue(exception.getMessage().contains("Deve conter uma Cidade"));
    }

    @Test
    public void deveSalvarApenasUmFrete(){
        //Continuação e preparação do Cenário
        freteRepository.save(frete);
        //Teste do Repositorio
        List<Frete> fretes = freteRepository.findAll();
        //Verificacao
        Assertions.assertEquals(1, fretes.size());
        //Limpeza do BD
        freteRepository.deleteAll();
    }

    @Test
    public void deveRemoverUmFrete() {
        //Continuação e preparação do cenário
        freteRepository.save(frete);
        //Teste do repositorio
        List<Frete> fretes = freteRepository.findAll();
        //Verificação
        assertEquals(1, fretes.size());

        //Continuacao e preparação do cenário
        freteRepository.deleteById(frete.getCodigoFrete());
        //Teste do repositorio
        List<Frete> resultado = freteRepository.findAll();
        //Verificação
        assertEquals(0, resultado.size());
    }


}