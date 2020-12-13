package sistema_de_frete.servico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.repositorio.CidadeRepository;
import sistema_de_frete.repositorio.ClienteRepository;
import sistema_de_frete.repositorio.FreteRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class FreteServiceImp implements FreteService {

    @Autowired
    private FreteRepository freteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Frete> buscarFretes(){
        return freteRepository.findAll();
    }

    @Override
    public Optional<Frete> buscarFrete(Long id ){
        System.out.println(id);
        return freteRepository.findById(id);}

    @Override
    public Frete cadastrarOuAlterar(Frete frete){
        if(frete.getCidade()==null){
            throw new NullPointerException("Cidade não existe");
        }
        if(frete.getCliente()==null){
            throw new NullPointerException("Cliente não existe");
        }

        if(!clienteRepository.findById(frete.getCliente().getCodigoCliente()).isPresent()){
            throw new NullPointerException("Cliente não encontrado");
        }
        if(!cidadeRepository.findById(frete.getCidade().getCodigoCidade()).isPresent()){
            throw new NullPointerException("Cidade não encontrada");
        }
        if(frete.getDescricao()==null){
            throw new NullPointerException("Descrição não existe");
        }
        frete.setValor(recuperarValorFrete(frete));
        return freteRepository.save(frete);
    }
    @Override
    public void remover(Long id) {
        freteRepository.deleteById(id);
    }

    @Override
    public Float recuperarValorFrete(Frete frete){
        float valorFrete = (frete.getPeso() * 10) + frete.getCidade().getTaxa();
        return valorFrete;
    }

    public Frete recuperarFreteMaiorValorCusto(){
        List<Frete> fretes = freteRepository.findAll(Sort.by("valorFrete").descending());
        Frete frete = fretes.get(fretes.size()-1);
        return frete;
    }

    /*public Cidade recuperarCidadeComMaisFretes(){
        cidadeRepository.todas(Sort.by())
    }*/
}
