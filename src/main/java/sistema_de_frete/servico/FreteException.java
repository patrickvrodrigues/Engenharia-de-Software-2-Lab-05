package sistema_de_frete.servico;

public class FreteException extends Exception {

    public FreteException(Exception e) throws FreteException {
        super(e);
    }

}
