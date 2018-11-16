package objetos;

public class Endereco {
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private Cidade cidade;

    public Endereco(String rua, String bairro, String numero, String complemento, Cidade cidade) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", bairro=" + bairro + ", numero=" + numero + ", complemento=" + complemento + ", cidade=" + cidade.toString() + '}';
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

    
}
