package sistema_de_frete.teste.integracao.repositorio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import sistema_de_frete.builder.CidadeBuilder;
import sistema_de_frete.builder.ClienteBuilder;
import sistema_de_frete.builder.FreteBuilder;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.repositorio.CidadeRepository;
import sistema_de_frete.repositorio.ClienteRepository;
import sistema_de_frete.repositorio.FreteRepository;

import java.util.List;
import java.util.Optional;


//@RunWith(SpringRunner.class)
@DataJpaTest
public class FreteRepositoryIntegrationQueryTest {

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

        //Cenário

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

    @AfterEach
    public void after() {
        //Limpar o BD
        freteRepository.deleteAll();
        clienteRepository.deleteAll();
        cidadeRepository.deleteAll();
    }


    @Test
    public void deveBuscarFretesPeloNomeCliente() {
        //Continuação e Preparação do Cenário
        frete = freteRepository.save(frete);
        frete1 = freteRepository.save(frete1);
        frete2 = freteRepository.save(frete2);

        //Teste do Repositorio
        List<Frete>fretesTeste = freteRepository.buscaPor(cliente);

        //Verificação
        for(Frete freteTeste : fretesTeste){
            Assertions.assertTrue(freteTeste.getCliente().equals(frete.getCliente()));
        }

    }

    @Test
    public void deveBuscarFretePeloCodigo() {
        //Continuação e Preparação do Cenário
        frete = freteRepository.save(frete);
        frete1 = freteRepository.save(frete1);
        frete2 = freteRepository.save(frete2);
        //Teste do Repositorio
        Optional<Frete> freteTeste = freteRepository.buscaPorCodigoFrete(frete.getCodigoFrete());
        //Verificação
        Assertions.assertTrue(freteTeste.get().equals(frete));
    }

    @Test
    public void deveRetornarTodosOsFretesOrdenadosEmOrdemCrescentePorNomeDaCidade()  {
        //Continuação e Preparação do Cenário
        frete = freteRepository.save(frete);
        frete1 = freteRepository.save(frete1);
        frete2 = freteRepository.save(frete2);
        List<Frete> fretes;

        //Teste do repositorio
        fretes = freteRepository.todos( Sort.by("cidade.nome").ascending() );

        //Verificação
        Assertions.assertTrue(fretes.get(0).getCidade().getNome().equals("Belém"));
        Assertions.assertTrue(fretes.get(1).getCidade().getNome().equals("Rio de Janeiro"));
        Assertions.assertTrue(fretes.get(2).getCidade().getNome().equals("São Luis"));




    }



}