package sistema_de_frete.repositorio;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;

import java.util.List;
import java.util.Optional;

public interface FreteRepository extends JpaRepository<Frete, Long> {
    @Query(value = "From Frete f where f.cliente = ?1")
    List<Frete> buscaPor(Cliente cliente);

    @Query(value = "from Frete f where f.codigoFrete = ?1")
    Optional<Frete> buscaPorCodigoFrete(long codigoFrete);

    @Query(value = "From Frete")
    List<Frete> todos(Sort sort);

}
