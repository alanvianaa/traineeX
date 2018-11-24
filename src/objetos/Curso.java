package objetos;

public class Curso {
    private int id;
    private String nome;
    private String turno;
    private Universidade universidade;

    public Curso(int id, String nome, String turno, Universidade universidade) {
        this.id = id;
        this.nome = nome;
        this.turno = turno;
        this.universidade = universidade;
    }
    
        public Curso(String nome, String turno, Universidade universidade) {
        this.nome = nome;
        this.turno = turno;
        this.universidade = universidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeUniversidade(){
        return universidade.getNome();
    }
    
    public String getEstado(){
        return universidade.getEndereco().getCidade().getEstado().getNome();
    }
    
    public String getSigla(){
        return universidade.getSigla();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    @Override
    public String toString() {
        return turno+" - "+nome;
    }
    
    
    
}
