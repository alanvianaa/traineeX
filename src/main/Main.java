package main;

import com.sun.javafx.robot.impl.FXRobotHelper;
import conexao.ConexaoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objetos.*;

public class Main extends Application {
    
    public static Stage tela;
   
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login/TelaLogin.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Login: Sistema TraineeX");
        stage.setScene(scene);
        stage.show();
        tela = stage;
 
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
        
        //System.out.println(universidade.toString());
    }

    
}
