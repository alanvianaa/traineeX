
package conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetos.Cidade;
import objetos.Endereco;
import objetos.Estado;
import objetos.Universidade;


public class ListagensDB extends ConexaoDB{
    
    public ObservableList universidades(String txtBuscaSigla,String txtBuscaNome, String txtBuscaEstado){
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

        String query = "SELECT * FROM universidade inner JOIN endereco on universidade.endereco_id = endereco.id inner join cidade c on endereco.cidade_id = c.id inner join estado e on c.estado_id = e.id "
                +bUni+";";
        ResultSet rs = buscarQuery(query);
        
        try {
            while(rs.next()){  
                
            Estado estado = new Estado(rs.getString("UF"),rs.getString("e.nome"));
            Cidade cidade = new Cidade(rs.getString("c.nome"),estado);
            Endereco endereco = new Endereco(rs.getString("rua"),rs.getString("bairro"),rs.getString("numero"),rs.getString("complemento"),cidade);
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
   
    
}
