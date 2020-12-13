package sistema_de_frete.repositorio;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sistema_de_frete.modelo.Cidade;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(value = "From Cidade")
    Cidade buscaPor(String nome);

    @Query(value = "From Cidade")
    List<Cidade> todas(Sort sort);
}
