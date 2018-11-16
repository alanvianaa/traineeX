
package objetos;

public class Universidade {
   private int id;
   private String sigla;
   private String nome;
   private Endereco endereco;

    public Universidade(int id, String sigla, String nome, Endereco endereco) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.endereco = endereco;
    }
   
     
    public Universidade(String sigla, String nome, Endereco endereco) {
        this.sigla = sigla;
        this.nome = nome;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Universidade{" + "id=" + id + ", sigla=" + sigla + ", nome=" + nome + ", \n endereco=" + endereco + '}';
    }
    public String getRua(){
        return endereco.getRua();
    }
    public String getNumero(){
        return endereco.getNumero();
    }
    public String getEstado(){
        return endereco.getCidade().getEstado().getNome();
    }
    public String getCidade(){
        return endereco.getCidade().getNome();
    }
    public String getBairro(){
        return endereco.getBairro();
    }
                
    public String getComplemento(){
        return endereco.getComplemento();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
   

   
}
