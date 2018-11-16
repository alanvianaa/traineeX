
package aplicacao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class ControladorTelaHome implements Initializable {
    
    
    @FXML
    private BarChart<?, ?> grafico1;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private BarChart<?, ?> grafico2;
    @FXML
    private NumberAxis x2;
    @FXML
    private CategoryAxis y2;
    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("OII ------------------> Oiii");
        XYChart.Series set1 = new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data("PA",50));
        set1.getData().add(new XYChart.Data("GO",100));
        set1.getData().add(new XYChart.Data("SP",80));
        set1.getData().add(new XYChart.Data("RJ",60));
        set1.getData().add(new XYChart.Data("MT",50));
        set1.getData().add(new XYChart.Data("MA",100));
        set1.getData().add(new XYChart.Data("CE",80));
        set1.getData().add(new XYChart.Data("AC",60));
        grafico1.getData().addAll(set1);
        
        System.out.println("OII ------------------> Oiii");
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("PA",50));
        set2.getData().add(new XYChart.Data("GO",100));
        set2.getData().add(new XYChart.Data("SP",80));
        set2.getData().add(new XYChart.Data("RJ",60));
        set2.getData().add(new XYChart.Data("MT",50));
        set2.getData().add(new XYChart.Data("MA",100));
        set2.getData().add(new XYChart.Data("CE",80));
        set2.getData().add(new XYChart.Data("AC",60));
        grafico2.getData().addAll(set2);
        
    }


    
}
