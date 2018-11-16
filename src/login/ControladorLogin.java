package login;

import main.Main;
import conexao.ConexaoDB;
import objetos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


public class ControladorLogin{
    
    
    
    @FXML
    private Label label;

    @FXML
    private TextField txtNome;
    
    @FXML
    private Label statusLogin;
 
    @FXML
    private PasswordField txtSenha;
    
    
    
    @FXML
    private void btnLogin(ActionEvent event) throws Exception {
        
        ConexaoDB con = new ConexaoDB();
        Usuario user = new Usuario();
        //Boolean validou = user.autenticacao(txtNome.getText(),txtSenha.getText());
        Boolean validou = true;
        
        
        if(validou == true){

                Stage stage = new Stage(); 
                Parent root = FXMLLoader.load(getClass().getResource("/aplicacao/TelaAplicacao.fxml")); 
                
                                
                Scene scene = new Scene(root); 
                stage.setScene(scene);
                stage.show();
                
                Main.tela.close();
                
        }else{
                statusLogin.setText("Ops! Login ou senha INCORRETO!");
                
                
        }
  
    }
    
    public void initialize(){
        System.out.println("Olá");
        
    }

  
  
}
