/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import conexao.ListagensDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objetos.Aluno;
import objetos.Curso;
import objetos.Universidade;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class TelaHomeController implements Initializable {

    @FXML
    private BarChart<?, ?> grafico1;
    @FXML
    private NumberAxis x2;
    @FXML
    private CategoryAxis y2;
    @FXML
    private BarChart<?, ?> grafico2;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private PieChart graficoPizza;
    @FXML
    private JFXToggleButton tbID;
    @FXML
    private JFXToggleButton ibAno;
    @FXML
    private JFXToggleButton tbNome;
    @FXML
    private JFXToggleButton tbCpf;
    @FXML
    private JFXToggleButton tbCurso;
    @FXML
    private JFXToggleButton tbTurno;
    @FXML
    private JFXToggleButton tbUniversidade;
    @FXML
    private JFXToggleButton tbEstado;
    @FXML
    private JFXToggleButton tbCidade;
    @FXML
    private JFXComboBox<?> cbEstado;
    @FXML
    private JFXComboBox<?> cbCidade;
    @FXML
    private JFXComboBox<Universidade> cbUniversidade;
    @FXML
    private JFXComboBox<Curso> cbCursos;
    @FXML
    private JFXComboBox<String> cbAno;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXCheckBox checkAutomatico;
    @FXML
    private TableView<Aluno> tabela;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_ano;
    @FXML
    private TableColumn<?, ?> col_nome;
    @FXML
    private TableColumn<?, ?> col_cpf;
    @FXML
    private TableColumn<?, ?> col_curso;
    @FXML
    private TableColumn<?, ?> col_turno;
    @FXML
    private TableColumn<?, ?> col_universidade;
    @FXML
    private TableColumn<?, ?> col_estado;
    @FXML
    private TableColumn<?, ?> col_cidade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListagensDB lista = new ListagensDB();
        
        ObservableList<PieChart.Data> dadosPizza = lista.pizza();
        
        graficoPizza.getData().addAll(dadosPizza);

        cbEstado.setItems(lista.estados());
        
        ObservableList<String>ano = FXCollections.observableArrayList();
        ano.add("2014");
        ano.add("2015");
        ano.add("2016");
        ano.add("2017");
        ano.add("2018");

        cbAno.setItems(ano);
        
        //select e2.UF, COUNT(e2.UF) as qtd  from aluno inner join curso c on aluno.curso_id = c.id inner join universidade u on c.universidade_id = u.id inner join endereco e on u.endereco_id = e.id inner join cidade c2 on e.cidade_id = c2.id inner join estado e2 on c2.estado_id = e2.id group by e2.UF;

        XYChart.Series set1 = lista.grafico(1);
        grafico1.getData().addAll(set1);
        
       
        XYChart.Series set2 = lista.grafico(2);
        grafico2.getData().addAll(set2);
        
    }    

    @FXML
    private void carregaCidade(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbCidade.setItems(lista.cidades((String) cbEstado.getValue()));
        carregaUniversidade(event);
        buscaAutomatica(event);
    }

    @FXML
    private void carregaUniversidade(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        String cidade;
        if(cbCidade.getValue()==null){
            cbUniversidade.setItems(lista.universidades((String) cbEstado.getValue(),null));
        }else{
            cbUniversidade.setItems(lista.universidades((String) cbEstado.getValue(), (String) cbCidade.getValue()));
        }
        buscaAutomatica(event);
    }

    @FXML
    private void carregaCursos(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbCursos.setItems(lista.cursos("",null,null,cbUniversidade.getValue()));
        buscaAutomatica(event);
    }
    

    @FXML
    private void buscar(ActionEvent event) {
        
        ListagensDB lista = new ListagensDB();  
  
        tabela.setItems(lista.alunos("","",cbUniversidade.getValue(),cbCursos.getValue(), (String) cbEstado.getValue(), (String) cbCidade.getValue(),cbAno.getValue()));

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        col_universidade.setCellValueFactory(new PropertyValueFactory<>("nomeUniversidade"));
        //col_nome.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_curso.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        col_turno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        col_ano.setCellValueFactory(new PropertyValueFactory<>("ano_ingresso"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col_cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        
    }

    @FXML
    private void filtrarColunas(ActionEvent event) {
        
        
        if(checkAutomatico.isSelected()){
            System.out.println("blabla");
        }
        if(tbID.isArmed()) col_id.setVisible(false);
        if(tbID.isSelected())col_id.setVisible(true);
        
        if(tbNome.isArmed()) col_nome.setVisible(false);
        if(tbNome.isSelected()) col_nome.setVisible(true);
        
        if(tbCpf.isArmed()) col_cpf.setVisible(false);
        if(tbCpf.isSelected()) col_cpf.setVisible(true);
        
        if(tbUniversidade.isArmed()) col_universidade.setVisible(false);
        if(tbUniversidade.isSelected()) col_universidade.setVisible(true);
        
        if(tbCurso.isArmed()) col_curso.setVisible(false);
        if(tbCurso.isSelected()) col_curso.setVisible(true);
        
        if(tbTurno.isArmed()) col_turno.setVisible(false);
        if(tbTurno.isSelected()) col_turno.setVisible(true);
        
        if(ibAno.isArmed()) col_ano.setVisible(false);
        if(ibAno.isSelected()) col_ano.setVisible(true);
        
        if(tbEstado.isArmed()) col_estado.setVisible(false);
        if(tbEstado.isSelected()) col_estado.setVisible(true);
        
        if(tbCidade.isArmed()) col_cidade.setVisible(false);
        if(tbCidade.isSelected()) col_cidade.setVisible(true);
 
    }

    @FXML
    private void AtivaCheckAutomatico(ActionEvent event) {
        if(checkAutomatico.isSelected()){
            btnBuscar.setDisable(true);
                
        }else{
            btnBuscar.setDisable(false);
        } 
    }

    @FXML
    private void buscaAutomatica(ActionEvent event) {
        if(checkAutomatico.isSelected()){
            buscar(event);
        }
    }
    
}
