package sistema_de_frete.modelo;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Frete {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long codigoFrete;

    @ManyToOne
    @JoinColumn(name="cidade")
    @NotNull(message = "Deve conter uma Cidade")
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name="cliente")
    @NotNull(message = "Deve conter um Cliente")
    private Cliente cliente;
    @NotEmpty(message="A Descricao deve ser preenchida")
    private String descricao;
    @DecimalMin(value = "1.0",message = "O valor mínimo deve ser 0.1") @NotNull(message = "O peso deve ser preenchido")
    private Float peso;
    @DecimalMin(value = "1.0",message = "O valor mínimo deve ser 0.1") @NotNull(message = "O valor deve ser preenchido")
    private Float valor;

    public long getCodigoFrete() {
        return codigoFrete;
    }

    public void setCodigoFrete(long codigoFrete) {
        this.codigoFrete = codigoFrete;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getValor() {
        return this.valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
