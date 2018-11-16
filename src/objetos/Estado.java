
package objetos;


public class Estado {
    private String UF;
    private String nome;

    public Estado(String UF, String nome) {
        this.UF = UF;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estado{" + "UF=" + UF + ", nome=" + nome + '}';
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
