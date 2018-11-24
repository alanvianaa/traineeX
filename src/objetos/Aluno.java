package objetos;


public class Aluno {
    private int id;
    private String nome;
    private String CPF;
    private Curso curso;
    private int ano_ingresso;

    public Aluno(String nome, String CPF, Curso curso, int ano_ingresso) {
        this.nome = nome;
        this.CPF = CPF;
        this.curso = curso;
        this.ano_ingresso = ano_ingresso;
    }

    public Aluno(int id, String nome, String CPF, Curso curso, int ano_ingresso) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.curso = curso;
        this.ano_ingresso = ano_ingresso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAno_ingresso() {
        return ano_ingresso;
    }

    public void setAno_ingresso(int ano_ingresso) {
        this.ano_ingresso = ano_ingresso;
    }
    
    public Universidade getUniversidade(){
        return curso.getUniversidade();
    }
    
    public String getNomeCurso(){
        return curso.getNome();
    }
    
    public String getTurno(){
        return curso.getTurno();
    }
    
    public String getEstado(){
        return curso.getUniversidade().getEstado();
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", nome=" + nome + ", CPF=" + CPF + ", curso=" + curso + ", ano_ingresso=" + ano_ingresso + '}';
    }
    
    
    
}
