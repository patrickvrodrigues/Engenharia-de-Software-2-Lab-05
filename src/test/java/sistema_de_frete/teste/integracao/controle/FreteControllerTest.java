package sistema_de_frete.teste.integracao.controle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sistema_de_frete.builder.CidadeBuilder;
import sistema_de_frete.builder.ClienteBuilder;
import sistema_de_frete.builder.FreteBuilder;
import sistema_de_frete.controle.FreteController;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.repositorio.CidadeRepository;
import sistema_de_frete.repositorio.ClienteRepository;
import sistema_de_frete.repositorio.FreteRepository;
import sistema_de_frete.servico.FreteException;

import java.net.URISyntaxException;
import java.util.Optional;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class FreteControllerTest {

    @MockBean
    private FreteRepository freteRepository;
    @MockBean
    private CidadeRepository cidadeRepository;
    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private FreteController freteController;

    @MockBean
    private Cliente cliente;
    @MockBean
    private Cidade cidade;
    private Frete frete;


    @BeforeEach
    public void start() {
        cliente = ClienteBuilder.umCliente().constroi();
        cidade =  CidadeBuilder.umaCidade().constroi();
        frete = FreteBuilder.umFrete().comCliente(cliente).comCidade(cidade).constroi();
    }

    @Test
    public void inserirFreteComClienteNuloDeveLancarException() throws FreteException, URISyntaxException {
        //Manipulação de consulta no BD
        Mockito.when(cidadeRepository.buscaPor(cidade.getNome())).thenReturn(cidade);
        Mockito.when(clienteRepository.buscaPor(cliente.getNome())).thenReturn(null);

        //Teste do Cenário
        frete.setCliente(null);
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class,
                        () -> freteController.inserir(frete),
                        "Deveria lançar um FreteException");
        //Verificação
        Assertions.assertTrue(exception.getMessage().contains("Cliente não existe"));

    }

    @Test
    public void inserirFreteComCidadeNuloDeveLancarException() throws FreteException {
        //Manipulação de consulta no BD
        Mockito.when(cidadeRepository.buscaPor(cidade.getNome())).thenReturn(null);
        Mockito.when(clienteRepository.buscaPor(cliente.getNome())).thenReturn(Optional.of(cliente));

        //Teste de Cenário
        frete.setCidade(null);
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class,
                        () -> freteController.inserir(frete),
                        "Deveria lançar um FreteException");

        //Verificação
        Assertions.assertTrue(exception.getMessage().contains("Cidade não existe"));



    }

    @Test
    public void inserirFreteComDescricaoNulaDeveLancarException() throws FreteException {
        //Manipulação de consulta no BD
        Mockito.when(cidadeRepository.findById(cidade.getCodigoCidade())).thenReturn(Optional.of(cidade));
        Mockito.when(clienteRepository.findById(cliente.getCodigoCliente())).thenReturn(Optional.of(cliente));

        //Teste de Cenário
        frete.setDescricao(null);
        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class,
                        () -> freteController.inserir(frete),

                        "Deveria lançar um FreteException");

        //Verificação
        Assertions.assertTrue(exception.getMessage().contains("Descrição não existe"));



    }

    @Test
    public void deveSalvarUmNovoFrete() throws FreteException, URISyntaxException {
        //Manipulação de consulta no BD
        Mockito.when(cidadeRepository.findById(cidade.getCodigoCidade())).thenReturn(Optional.of(cidade));
        Mockito.when(clienteRepository.findById(cliente.getCodigoCliente())).thenReturn(Optional.of(cliente));

        //Teste de Cenário
        freteController.inserir(frete);

        //Verificação
        Mockito.verify(freteRepository, Mockito.times(1))
                .save(frete);
    }

    @Test
    public void deveRemoverFrete() {

        //Teste de Cenário
        freteController.remover(1L);

        //Verificação
        Mockito.verify(freteRepository,
                Mockito.times(1))
                .deleteById(1L);
    }

}