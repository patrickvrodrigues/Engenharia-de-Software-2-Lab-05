package sistema_de_frete.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long codigoCliente;

    @NotEmpty(message="O Nome deve ser preenchido")
    private String nome;
    @NotEmpty(message="O Endereco deve ser preenchido")
    private String endereco;
    @NotEmpty(message="O telefone deve ser preenchido")
    private String telefone;

    public Cliente(){}

    public long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigoCliente=" + codigoCliente +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
