package sistema_de_frete.builder;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import sistema_de_frete.modelo.Cidade;
import sistema_de_frete.modelo.Cliente;
import sistema_de_frete.modelo.Frete;

public class FreteBuilder {
    private Frete frete;

    private FreteBuilder(){}

    public static FreteBuilder umFrete() {
        FreteBuilder builder = new FreteBuilder();

        builder.frete =new Frete();

        builder.frete.setCliente(ClienteBuilder.umCliente().constroi());
        builder.frete.setCidade(CidadeBuilder.umaCidade().constroi());
        builder.frete.setDescricao("Teste");
        builder.frete.setPeso(120F);

        return  builder;
    }

    public FreteBuilder comCliente(Cliente cliente){
        this.frete.setCliente(cliente);
        return this;
    }

    public FreteBuilder comCidade(Cidade cidade){
        this.frete.setCidade(cidade);
        return this;
    }

    public FreteBuilder comDescricao(String descricao){
        this.frete.setDescricao(descricao);
        return this;
    }
    public FreteBuilder comPeso(float peso){
        this.frete.setPeso(peso);
        return this;
    }

    public Frete constroi(){
        return this.frete;
    }

}
