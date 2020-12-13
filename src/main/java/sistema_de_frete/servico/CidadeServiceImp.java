package sistema_de_frete.servico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.repositorio.CidadeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeServiceImp implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<Cidade> buscarCidades(){
        return cidadeRepository.findAll();
    }

    @Override
    public Optional<Cidade> buscarCidade(Long id ){ return cidadeRepository.findById(id);}

    @Override
    public Cidade cadastrarOuAlterar(Cidade cidade){
        return cidadeRepository.save(cidade);
    }
    @Override
    public void remover(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public Cidade buscaPor(String nome){return cidadeRepository.buscaPor(nome); }
}
