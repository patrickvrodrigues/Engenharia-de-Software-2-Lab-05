package sistema_de_frete.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sistema_de_frete.modelo.Cliente;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    @Query(value = "From Cliente")
    Optional<Cliente> findFirstByTelefone(String telefone);
    @Query(value = "From Cliente")
    Optional<Cliente> buscaPor(String nome);
}
