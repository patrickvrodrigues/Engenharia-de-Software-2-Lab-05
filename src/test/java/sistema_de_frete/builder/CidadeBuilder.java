package sistema_de_frete.builder;

import sistema_de_frete.modelo.Cidade;

public class CidadeBuilder {
    private Cidade cidade;

    private CidadeBuilder(){}

    public static CidadeBuilder umaCidade(){
        CidadeBuilder builder = new CidadeBuilder();

        builder.cidade = new Cidade();

        builder.cidade.setNome("Rio de Janeiro");
        builder.cidade.setTaxa(40.0F);
        builder.cidade.setUf("RJ");

        return builder;
    }

    public CidadeBuilder comNome(String nome){
        this.cidade.setNome(nome);
        return this;
    }

    public CidadeBuilder comTaxa(float taxa){
        this.cidade.setTaxa(taxa);
        return this;
    }

    public CidadeBuilder comUF(String uf){
        this.cidade.setUf(uf);
        return this;
    }

    public CidadeBuilder comCodigo(long codigo){
        this.cidade.setCodigoCidade(codigo);
        return this;
    }
    public Cidade constroi(){
        return this.cidade;
    }
}
