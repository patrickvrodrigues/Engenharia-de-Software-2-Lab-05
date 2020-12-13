package sistema_de_frete.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.servico.CidadeService;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/")
    public ResponseEntity<List<Cidade>> cidades(){
        List<Cidade> cidades = cidadeService.buscarCidades();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<Cidade> cidade(@PathVariable Long id){
        return cidadeService.buscarCidade(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cidade> inserir(@RequestBody @Valid Cidade cidade) throws URISyntaxException {
        cidade = cidadeService.cadastrarOuAlterar(cidade);
        return new ResponseEntity<>(cidade, HttpStatus.CREATED);
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Cidade> alterar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) throws URISyntaxException {
        cidade = cidadeService.cadastrarOuAlterar(cidade);
        return new ResponseEntity<>(cidade, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Cidade> remover(@PathVariable Long id) {
        cidadeService.remover(id);
        return ResponseEntity.noContent().build();
    }

}


