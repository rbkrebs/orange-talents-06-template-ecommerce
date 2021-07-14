package br.com.zupacademy.romulo.ecommerce.validadores;

public class ErroCampo {

    private String campo;
    private String mensagem;

    public ErroCampo(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
