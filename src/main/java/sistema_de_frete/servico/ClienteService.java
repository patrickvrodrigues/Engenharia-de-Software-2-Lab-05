package sistema_de_frete.servico;

import sistema_de_frete.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> buscarClientes();
    Optional<Cliente> buscarCliente(Long id);
    Optional<Cliente> buscaPor(String telefone);
    Cliente cadastrarOuAlterar(Cliente cliente);
    void remover(Long id);
}
