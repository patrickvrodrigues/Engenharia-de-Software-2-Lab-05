package sistema_de_frete.modelo;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long codigoCidade;

    @NotEmpty(message="O Nome deve ser preenchido")
    private String nome;

    @NotEmpty(message="O Estado deve ser preenchido")
    private String uf;

    @DecimalMin("1.0")
    private Float taxa;


    public Cidade(){}

    public long getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(long codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Float getTaxa() {
        return taxa;
    }

    public void setTaxa(Float taxa) {
        this.taxa = taxa;
    }

}
