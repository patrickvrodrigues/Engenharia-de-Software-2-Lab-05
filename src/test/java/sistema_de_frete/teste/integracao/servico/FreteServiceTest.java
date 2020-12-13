package sistema_de_frete.teste.integracao.servico;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sistema_de_frete.builder.CidadeBuilder;
import sistema_de_frete.builder.ClienteBuilder;
import sistema_de_frete.builder.FreteBuilder;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.repositorio.CidadeRepository;
import sistema_de_frete.repositorio.ClienteRepository;
import sistema_de_frete.repositorio.FreteRepository;
import sistema_de_frete.servico.FreteService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class FreteServiceTest {

    @Autowired
    private FreteService freteService;

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Frete frete;
    private Cliente cliente;
    private Cidade cidade;

    @BeforeEach
    public void before() {

        cidade = CidadeBuilder.umaCidade().constroi();
        cidade = cidadeRepository.save(cidade);
        cliente = ClienteBuilder.umCliente().constroi();
        cliente = clienteRepository.save(cliente);
        frete = FreteBuilder.umFrete().comCliente(cliente).comCidade(cidade).constroi();
    }

    @Test
    public void insereComClienteNuloDeveLancarException()  {

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> {  frete.setCliente(null);
                    freteService.cadastrarOuAlterar(frete);
                },
                "Deveria lançar um NullPointerException");

        assertTrue(exception.getMessage().contains("Cliente não existe"));

    }

    @Test
    public void insereComClienteNãoCadastradoDeveLancarException()  {
        Cliente clienteTest = ClienteBuilder.umCliente().comCodigo(999l).constroi();
        frete.setCliente(clienteTest);
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> {
                    freteService.cadastrarOuAlterar(frete);
                },
                "Deveria lançar um NullPointerException");

        assertTrue(exception.getMessage().contains("Cliente não encontrado"));

    }

    @Test
    public void insereComCidadeNulaDeveLancarException()  {

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> {  frete.setCidade(null);
                    freteService.cadastrarOuAlterar(frete);
                },
                "Deveria lançar um NullPointerException");

        assertTrue(exception.getMessage().contains("Cidade não existe"));

    }

    @Test
    public void insereComCidadeNãoCadastradaDeveLancarException()  {
        Cidade cidadeTest = CidadeBuilder.umaCidade().comCodigo(999l).constroi();
        frete.setCidade(cidadeTest);
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> {
                    freteService.cadastrarOuAlterar(frete);
                },
                "Deveria lançar um NullPointerException");

        assertTrue(exception.getMessage().contains("Cidade não encontrada"));

    }

    @Test
    public void deveSalvarUmNovoFrete()  {

        freteService.cadastrarOuAlterar(frete);
        List<Frete> fretes = freteRepository.findAll();

        Assertions.assertEquals(1, fretes.size());
        freteRepository.deleteAll();
    }

}