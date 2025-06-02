package excecoes;

public class RequisicaoInvalidaException extends RuntimeException{
    private String message;

    public RequisicaoInvalidaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
