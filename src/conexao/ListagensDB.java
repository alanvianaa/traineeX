
package conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    public ObservableList cursos(String txtCurso,String txtTurno, String txtBuscaEstado){
        //Vai criar os Objetos Universidades buscando as informações no banco de dados e colocar em um ObservableList do JavaFX
        ObservableList<Curso> lista = FXCollections.observableArrayList();
        
        String bUni = "";
        if(!txtCurso.equals("")){
            bUni = "WHERE u.sigla = '"+txtCurso+"'";          
        }     
        if(txtTurno != null){
            bUni = "WHERE curso.turno = '"+txtTurno+"'"; 
        }
        if(txtBuscaEstado != null){
            bUni = "WHERE e2.nome = '"+txtBuscaEstado+"'"; 
        }
        
        
        
        String query = "SELECT curso.id,curso.nome,curso.turno,curso.universidade_id FROM curso INNER JOIN universidade u on curso.universidade_id = u.id INNER JOIN endereco e on u.endereco_id = e.id INNER JOIN cidade c on e.cidade_id = c.id INNER JOIN estado e2 on c.estado_id = e2.id "+bUni+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            
            while(rs.next()){  
            
            String i = Integer.toString(rs.getInt("universidade_id"));
                       
            Universidade uni = universidade("","","",i);              
            Curso curso = new Curso(rs.getInt("id"),rs.getString("nome"),rs.getString("turno"),uni);
            
            lista.add(curso);
            
            }
            
        } catch (SQLException ex) {
            System.out.println("ERRO AQUI!!! -> Bla Bla Bla! ################################################ ");
        }
        
        return lista;
    }
    
}
