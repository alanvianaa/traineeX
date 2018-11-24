
package conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Aluno;
import objetos.Cidade;
import objetos.Curso;
import objetos.Endereco;
import objetos.Estado;
import objetos.Universidade;


public class ListagensDB extends ConexaoDB{
    
    public ObservableList universidades(String txtBuscaSigla,String txtBuscaNome, String txtBuscaEstado,String idUni){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<Universidade> lista = FXCollections.observableArrayList();
        
        String bUni = "";
        if(!txtBuscaSigla.equals("")){
            bUni = "WHERE sigla = '"+txtBuscaSigla+"'";          
        }
        if(!txtBuscaNome.equals("")){
            bUni = "WHERE universidade.nome = '"+txtBuscaNome+"'"; 
        }
        if(txtBuscaEstado != null){
            bUni = "WHERE e.nome = '"+txtBuscaEstado+"'"; 
        }
        if(!idUni.equals("")){
            bUni = "WHERE universidade.id = '"+idUni+"'"; 
        }

        String query = "SELECT * FROM universidade inner JOIN endereco on universidade.endereco_id = endereco.id inner join cidade c on endereco.cidade_id = c.id inner join estado e on c.estado_id = e.id "
                +bUni+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            while(rs.next()){  
                
            Estado estado = new Estado(rs.getInt("e.id"),rs.getString("UF"),rs.getString("e.nome"));
                
            Cidade cidade = new Cidade(rs.getInt("c.id"),rs.getString("c.nome"),estado);
            Endereco endereco = new Endereco(rs.getInt("endereco.id"),rs.getString("rua"),rs.getString("bairro"),rs.getString("numero"),rs.getString("complemento"),cidade);
            Universidade uni = new Universidade(rs.getInt("universidade.id"), rs.getString("sigla"),rs.getString("universidade.nome"),endereco);
            
            lista.add(uni);
            System.out.println(uni);
            }
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
    
    public ObservableList estados(){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<String> lista = FXCollections.observableArrayList();
        
        String query = "SELECT * FROM estado;";
        ResultSet rs = buscarQuery(query);
        
        try {
            while(rs.next()){  
                
             lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
    
    public ObservableList cidades(String estado){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<String> lista = FXCollections.observableArrayList();
        
        String query = "SELECT cidade.nome FROM cidade INNER JOIN estado e on cidade.estado_id = e.id where e.nome = '"+estado+"';";
        ResultSet rs = buscarQuery(query);
        
        try {
            while(rs.next()){  
                
             lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
   
    public Universidade universidade(String txtBuscaSigla,String txtBuscaNome, String txtBuscaEstado,String idUni){

        String bUni = "";
        if(!txtBuscaSigla.equals("")){
            bUni = "WHERE sigla = '"+txtBuscaSigla+"'";          
        }
        if(!txtBuscaNome.equals("")){
            bUni = "WHERE universidade.nome = '"+txtBuscaNome+"'"; 
        }
        if(txtBuscaEstado != null){
            bUni = "WHERE e.nome = '"+txtBuscaEstado+"'"; 
        }
        if(!idUni.equals("")){
            bUni = "WHERE universidade.id = '"+idUni+"'"; 
        }

        String query = "SELECT * FROM universidade inner JOIN endereco on universidade.endereco_id = endereco.id inner join cidade c on endereco.cidade_id = c.id inner join estado e on c.estado_id = e.id "
                +bUni+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            while(rs.next()){  
                
            Estado estado = new Estado(rs.getInt("e.id"),rs.getString("UF"),rs.getString("e.nome"));
                
            Cidade cidade = new Cidade(rs.getInt("c.id"),rs.getString("c.nome"),estado);
            Endereco endereco = new Endereco(rs.getInt("endereco.id"),rs.getString("rua"),rs.getString("bairro"),rs.getString("numero"),rs.getString("complemento"),cidade);
            Universidade uni = new Universidade(rs.getInt("universidade.id"), rs.getString("sigla"),rs.getString("universidade.nome"),endereco);
            
            return uni;
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return null;
    }
    
    public ObservableList cursos(String txtCurso,String txtTurno, String txtBuscaEstado, Universidade universidade){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<Curso> lista = FXCollections.observableArrayList();
        
        String bCur = "";
        if(!txtCurso.equals("")){
            bCur = "WHERE u.sigla = '"+txtCurso+"'";          
        }     
        if(txtTurno != null){
            bCur = "WHERE curso.turno = '"+txtTurno+"'"; 
        }
        if(txtBuscaEstado != null){
            bCur = "WHERE e2.nome = '"+txtBuscaEstado+"'"; 
        }
        if(universidade != null){
            bCur = "WHERE u.nome = '"+universidade.getNome()+"' AND e2.nome = '"+universidade.getEstado()+"' AND c.nome = '"+universidade.getCidade()+"'"; 
        
        }
        
        
        String query = "SELECT curso.id,curso.nome,curso.turno,curso.universidade_id FROM curso INNER JOIN universidade u on curso.universidade_id = u.id INNER JOIN endereco e on u.endereco_id = e.id INNER JOIN cidade c on e.cidade_id = c.id INNER JOIN estado e2 on c.estado_id = e2.id "+bCur+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            
            while(rs.next()){  
            
            String i = Integer.toString(rs.getInt("curso.universidade_id"));
            System.out.println("Chgou AQUIIII-->"+i);           
            Universidade uni = universidade("","","",i);              
            Curso curso = new Curso(rs.getInt("id"),rs.getString("nome"),rs.getString("turno"),uni);
            
            lista.add(curso);
            
            }
            
        } catch (SQLException ex) {
            System.out.println("ERRO AQUI!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
    
    public Curso curso(String idCurso){

        String bCur = "";
        
        if(!idCurso.equals("")){
            bCur = "WHERE curso.id = '"+idCurso+"'"; 
        }

        String query = "SELECT curso.id,curso.nome,curso.turno,curso.universidade_id FROM curso INNER JOIN universidade u on curso.universidade_id = u.id INNER JOIN endereco e on u.endereco_id = e.id INNER JOIN cidade c on e.cidade_id = c.id INNER JOIN estado e2 on c.estado_id = e2.id "+bCur+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            
            while(rs.next()){  
            
            String i = Integer.toString(rs.getInt("curso.universidade_id"));
                       
            Universidade uni = universidade("","","",i);              
            Curso curso = new Curso(rs.getInt("id"),rs.getString("nome"),rs.getString("turno"),uni);
            
            return curso;
            
            }  
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return null;
    }
    
    public ObservableList alunos(String nome,String cpf,Universidade universidade,Curso cur, String txtBuscaEstado, String txtAno){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<Aluno> lista = FXCollections.observableArrayList();
        
        String bAluno = "";
        
        if(!nome.equals("")){
            bAluno = "WHERE aluno.nome = '"+nome+"'";          
        }
        
        if(!cpf.equals("")){
            bAluno = "WHERE aluno.cpf = '"+cpf+"'";          
        }
        
        if(txtBuscaEstado != null){
            bAluno = "WHERE e2.nome = '"+txtBuscaEstado+"'"; 
        }
        
        if(universidade != null){
            bAluno = "WHERE u.id = "+universidade.getId(); 
        }
        
        if(cur != null){
            bAluno = "WHERE c.id = '"+cur.getId()+"'";          
        }     

        if(txtAno != null){
            if(bAluno.equals("")){
               bAluno = "WHERE aluno.ano_ingresso = '"+txtAno+"'"; 
            }else{
                bAluno = bAluno+" AND aluno.ano_ingresso = '"+txtAno+"'"; 
            }
             
        }
        
        
        String query = "select * from aluno inner JOIN curso c on aluno.curso_id = c.id inner join universidade u on c.universidade_id = u.id inner Join endereco e on u.endereco_id = e.id inner join cidade c2 on e.cidade_id = c2.id inner join estado e2 on c2.estado_id = e2.id "+bAluno+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            
            while(rs.next()){  
            
            String i = Integer.toString(rs.getInt("aluno.curso_id"));              
            cur = curso(i);              
            System.out.println("OPAAA Chegou aqui!"); 
            Aluno aluno = new Aluno(rs.getInt("aluno.id"),rs.getString("aluno.nome"),rs.getString("aluno.cpf"),cur,rs.getInt("aluno.ano_ingresso"));
            
            lista.add(aluno);
            
            }
            
        } catch (SQLException ex) {
            System.out.println("ERRO AQUI!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
    
    
    
}
