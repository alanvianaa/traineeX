
package objetos;


public class Estado {
    private int id;
    private String UF;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado(int id, String UF, String nome) {
        this.id = id;
        this.UF = UF;
        this.nome = nome;
    }

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
