
package conexao;

import aplicacao.ControladorAplicacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import objetos.*;

public class ConexaoDB {
    String servidor="192.168.0.105";
    String nomeBanco="trainee";
    String login="root";
    String senha="1048576";
    //Connection con;
    
    public Connection conectar(){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+nomeBanco,login,senha);
        } catch (SQLException ex) {
           System.out.println("Erro de conexão com banco de dados!");
        }
        return null;
    }    
    
    public void inserirQuery(String query){
        System.out.println(query);
        try {
            PreparedStatement stmt;
            stmt = conectar().prepareStatement(query);
            stmt.execute();
            
        } catch (SQLException ex) {
 
            System.out.println("Erro de Script SQL na Insersão");
            
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        
  
    }
    
    public ResultSet buscarQuery(String query){
        System.out.println(query);
        try {
            PreparedStatement stmt;
            stmt = conectar().prepareStatement(query);
            ResultSet rs;
            rs = stmt.executeQuery();

            return rs;
        } catch (SQLException ex) {
            System.out.println("Erro de Script SQL na Busca");
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }
    
    
    private String buscaCidade(Cidade cidade){
        //Busca o ID da Cidade
        String query = "SELECT * FROM cidade WHERE nome = '"+cidade.getNome()+"';";

        ResultSet rs = buscarQuery(query);
            
        try {
            while(rs.next()){
                //System.out.println("ID: "+rs.getString("id")+" Nome: "+rs.getString("nome")+" Estado: "+rs.getString("estado_id"));
                return rs.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Não encontrou a Cidade! ################################################ ");
        }
        return "";
    }
    
    private String gravaEndereco(Endereco endereco){
        //Grava o endereco no banco de dados e retorna o ID do endereco;
        
       String idCidade = buscaCidade(endereco.getCidade()); 
       String query = "INSERT INTO `endereco` (`rua`, `bairro`, `numero`, `complemento`, `cidade_id`) VALUES ('"+endereco.getRua()+"', '"+endereco.getBairro()+"',"+endereco.getNumero()+", '"+endereco.getComplemento()+"', "+idCidade+");";
       inserirQuery(query);
       
       ResultSet rs= buscarQuery("SELECT * FROM endereco where rua = '"+endereco.getRua()+"' AND bairro = '"+endereco.getBairro()+"' AND numero = "+endereco.getNumero()+" AND complemento = '"+endereco.getComplemento()+"' AND cidade_id = "+idCidade+";");
       
        try {
            while(rs.next()){
                return rs.getString("id");
            }
        } catch (SQLException ex) {
            System.out.println("ERRO!!! -> Não encontrou o Endereco! ################################################ ");
        }
       
        return "";
    }
    
    public void gravarUniversidade(Universidade universidade){
        
        String endereco_id = gravaEndereco(universidade.getEndereco());
        
        String query = "INSERT INTO `universidade` (`sigla`, `nome`, `endereco_id`) VALUES ('"+universidade.getSigla()+"','"+universidade.getNome()+"',"+endereco_id+");";
        
        inserirQuery(query);
        
        
    }
    
    
     
}
