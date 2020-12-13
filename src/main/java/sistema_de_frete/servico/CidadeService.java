package sistema_de_frete.servico;

import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface CidadeService {

    List<Cidade> buscarCidades();
    Optional<Cidade> buscarCidade(Long id);
    Cidade cadastrarOuAlterar(Cidade cidade);
    void remover(Long id);
    Cidade buscaPor(String nome);
}
