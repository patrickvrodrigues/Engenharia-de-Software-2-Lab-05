package sistema_de_frete.servico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.repositorio.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> buscarClientes(){
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarCliente(Long id ){ return clienteRepository.findById(id);}

    @Override
    public Optional<Cliente> buscaPor(String telefone){return clienteRepository.findFirstByTelefone(telefone); }

    @Override
    public Cliente cadastrarOuAlterar(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    @Override
    public void remover(Long id) {
        clienteRepository.deleteById(id);
    }
}
