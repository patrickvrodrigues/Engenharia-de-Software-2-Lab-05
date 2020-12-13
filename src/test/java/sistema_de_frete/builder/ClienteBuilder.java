package sistema_de_frete.builder;

import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ClienteBuilder {
    private Cliente cliente;

    private ClienteBuilder(){}

    public static ClienteBuilder umCliente() {
        ClienteBuilder builder = new ClienteBuilder();

        builder.cliente = new Cliente();

        builder.cliente.setNome("Patrick Viegas");
        builder.cliente.setEndereco("Rua estrela");
        builder.cliente.setTelefone("123456789");

        return builder;
    }

    public ClienteBuilder comNome(String nome){
        this.cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comEndereco(String endereco){
        this.cliente.setEndereco(endereco);
        return this;
    }

    public ClienteBuilder comCodigo(long codigo){
        this.cliente.setCodigoCliente(codigo);
        return this;
    }

    public Cliente constroi(){
        return this.cliente;
    }
}
