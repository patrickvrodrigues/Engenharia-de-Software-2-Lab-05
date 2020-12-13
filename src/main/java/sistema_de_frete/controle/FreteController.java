package sistema_de_frete.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_frete.modelo.Frete;
import sistema_de_frete.servico.FreteService;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/frete")
public class FreteController {
    @Autowired
    private FreteService freteService;

    @GetMapping("/")
    public ResponseEntity<List<Frete>> fretes(){
        List<Frete> fretes = freteService.buscarFretes();
        return ResponseEntity.ok(fretes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frete> frete(@PathVariable Long id){
        return freteService.buscarFrete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/inserir")
    public ResponseEntity<Frete> inserir(@RequestBody @Valid Frete frete) throws URISyntaxException {
        frete = freteService.cadastrarOuAlterar(frete);
        return new ResponseEntity<>(frete, HttpStatus.CREATED);
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Frete> alterar(@PathVariable Long id, @RequestBody @Valid Frete frete) throws URISyntaxException {
        frete = freteService.cadastrarOuAlterar(frete);
        return new ResponseEntity<>(frete, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Frete> remover(@PathVariable Long id) {
        freteService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
