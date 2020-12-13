package sistema_de_frete.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.servico.ClienteService;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> clientes(){
        List<Cliente> clientes = clienteService.buscarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> cliente(@PathVariable Long id){
        return clienteService.buscarCliente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cliente> inserir(@RequestBody @Valid Cliente cliente) throws URISyntaxException {
        cliente = clienteService.cadastrarOuAlterar(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Cliente> alterar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) throws URISyntaxException {
        cliente = clienteService.cadastrarOuAlterar(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Cliente> remover(@PathVariable Long id) {
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
