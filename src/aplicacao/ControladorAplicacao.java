
package aplicacao;

import com.jfoenix.controls.JFXButton;
import conexao.ConexaoDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.ControladorLogin;


public class ControladorAplicacao extends ControladorLogin{
    
    @FXML
    private Circle bolinhaStatus;
    
    @FXML
    private BorderPane boderpane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnUniversidade;
    @FXML
    private JFXButton btnCursos;
    @FXML
    private JFXButton btnAlunos;
    
    

    @FXML
    public void btnClose(){
        Stage stage = (Stage) boderpane.getScene().getWindow();
        
        System.out.println("OI");
        stage.close();
    }
    
        
    @Override
    public void initialize() {

        alteraBoderPaneCenter("/aplicacao/TelaHome.fxml");

  
    }    
    
    public void alteraBoderPaneCenter(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(tela));
        } catch (IOException ex) {
            Logger.getLogger(ControladorAplicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        boderpane.setCenter(root);
    }
    
    @FXML
    public void btnHome(){
        alteraBoderPaneCenter("/aplicacao/TelaHome.fxml");
    }
    
    @FXML
    public void btnUniversidade(){
        alteraBoderPaneCenter("/aplicacao/TelaUniversidade.fxml");
    }
    
    @FXML
    public void btnCurso(){
        alteraBoderPaneCenter("/aplicacao/TelaCurso.fxml");
    }
    
    public void btnOK(){
 
    }

    @FXML
    private void btnAluno(ActionEvent event) {
        alteraBoderPaneCenter("/aplicacao/TelaAluno.fxml");
    }
    
    
}
