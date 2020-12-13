package sistema_de_frete.servico;

import sistema_de_frete.modelo.Frete;

import java.util.List;
import java.util.Optional;

public interface FreteService {

    List<Frete> buscarFretes();
    Optional<Frete> buscarFrete(Long id);
    Frete cadastrarOuAlterar(Frete frete);
    void remover(Long id);
    Float recuperarValorFrete(Frete frete);
}
