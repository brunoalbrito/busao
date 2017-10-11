package br.com.panelinha.model;

/**
 *
 * @author Bruno
 */
public class Linha {

    private int codigoLinha;
    private String primeiroLetreiro;
    private int segundoLetreiro;
    /**
     * Sentido de operação onde 1 significa de Terminal Principal para Terminal
     * Secundário e 2 de Terminal Secundário para Terminal Principal
     */
    private int sentidoOperacional;
    private String terminalPrincipal;
    private String terminalSegundario;

    public Linha() {
    }

    public Linha(int codigoLinha, String primeiroLetreiro, int segundoLetreiro, int sentidoOperacional, String terminalPrincipal, String terminalSegundario) {
        this.codigoLinha = codigoLinha;
        this.primeiroLetreiro = primeiroLetreiro;
        this.segundoLetreiro = segundoLetreiro;
        this.sentidoOperacional = sentidoOperacional;
        this.terminalPrincipal = terminalPrincipal;
        this.terminalSegundario = terminalSegundario;
    }

    public int getCodigoLinha() {
        return codigoLinha;
    }

    public void setCodigoLinha(int codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    public String getPrimeiroLetreiro() {
        return primeiroLetreiro;
    }

    public void setPrimeiroLetreiro(String primeiroLetreiro) {
        this.primeiroLetreiro = primeiroLetreiro;
    }

    public int getSegundoLetreiro() {
        return segundoLetreiro;
    }

    public void setSegundoLetreiro(int segundoLetreiro) {
        this.segundoLetreiro = segundoLetreiro;
    }

    public int getSentidoOperacional() {
        return sentidoOperacional;
    }

    public void setSentidoOperacional(int sentidoOperacional) {
        this.sentidoOperacional = sentidoOperacional;
    }

    public String getTerminalPrincipal() {
        return terminalPrincipal;
    }

    public void setTerminalPrincipal(String terminalPrincipal) {
        this.terminalPrincipal = terminalPrincipal;
    }

    public String getTerminalSegundario() {
        return terminalSegundario;
    }

    public void setTerminalSegundario(String terminalSegundario) {
        this.terminalSegundario = terminalSegundario;
    }

    @Override
    public String toString() {
        String texto = "Linha{" + "codigoLinha=" + codigoLinha + ", primeiroLetreiro=" + primeiroLetreiro + ", segundoLetreiro=" + segundoLetreiro + ", sentidoOperacional=" + sentidoOperacional;

        if (sentidoOperacional == 1) {
            texto += ", terminalPartida=" + terminalPrincipal + ", terminalChegada=" + terminalSegundario + '}';
        } else if(sentidoOperacional == 2) {
            texto += ", terminalPartida=" + terminalSegundario + ", terminalChegada=" + terminalPrincipal + '}';
        }

        return texto;
    }

}
