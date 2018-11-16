package objetos;

import conexao.ConexaoDB;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private String login;
    private String email;
    private String senha;
    private int nivel;

    public Usuario(String login, String email, String senha, int nivel) {
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(){

    }    
   
    public boolean autenticacao(String login, String senha){
        
        String query = "SELECT * FROM usuario where login = '"+login+"' AND senha = '"+senha+"';";
        ConexaoDB con = new ConexaoDB();
        ResultSet rs;
        rs = con.buscarQuery(query);
 
        try {
            while(rs.next()){

                this.login = rs.getString("login");
                this.email = rs.getString("email");
                this.senha = rs.getString("senha");
                this.nivel = rs.getInt("nivel");
                
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro da busca");
            return false;
            
        }
        System.out.println("Não encontrou o usuário senha");
        return false;
    }
    
}