/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import conexao.ConexaoDB;
import conexao.ListagensDB;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objetos.Curso;
import objetos.Universidade;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class ControladorTelaCurso implements Initializable {

    @FXML
    private JFXTextField txtbuscaCurso;
    @FXML
    private JFXComboBox<String> cbEstados;
    @FXML
    private JFXComboBox<Universidade> cbUniversidade;
    @FXML
    private Label label1;
    @FXML
    private TableView<Curso> tabela1;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_sigla;
    @FXML
    private TableColumn<?, ?> col_universidade;
    @FXML
    private TableColumn<?, ?> col_curso;
    @FXML
    private TableColumn<?, ?> col_turno;
    @FXML
    private TableColumn<?, ?> col_qtdAlunos;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXTextField txtCurso;
    @FXML
    private JFXComboBox<String> cbTurno;
    @FXML
    private JFXComboBox<String> cbEstados2;
    @FXML
    private JFXComboBox<Universidade> cbUniversidade2;
    @FXML
    private Label statusGravacao;
    @FXML
    private JFXComboBox<String> cbTurnos;
    @FXML
    private TableColumn<?, ?> col_estado;
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ListagensDB lista = new ListagensDB();
        
        cbEstados.setItems(lista.estados());

        cbEstados2.setItems(lista.estados());
        
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        
        ObservableList<String>turnos = FXCollections.observableArrayList();
        turnos.add("Manhã");
        turnos.add("Tarde");
        turnos.add("Noite");
        turnos.add("Integral");
        
        cbTurnos.setItems(turnos);
        cbTurno.setItems(turnos);
        
    }    

    @FXML
    private void carregaUniversidades(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        String estado = cbEstados.getValue();
        ObservableList<Universidade> oi = lista.universidades("","",estado,"");
        cbUniversidade.setItems(oi);
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        
        ListagensDB lista = new ListagensDB();  
  
        tabela1.setItems(lista.cursos(txtbuscaCurso.getText(),cbTurnos.getValue(),cbEstados.getValue()));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_sigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col_universidade.setCellValueFactory(new PropertyValueFactory<>("nomeUniversidade"));
        //col_nome.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_curso.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_turno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        col_qtdAlunos.setCellValueFactory(new PropertyValueFactory<>("alunos"));
        
    }

    @FXML
    private void ativaEdicao(MouseEvent event) {
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @FXML
    private void editarCurso(ActionEvent event) throws IOException {
        Curso curso = tabela1.getSelectionModel().getSelectedItem();
            
            Passagens.cur = curso;
                Stage stage = new Stage();
               
                Parent root = FXMLLoader.load(getClass().getResource("/aplicacao/Popup2.fxml")); 
                Passagens.stage = stage;      
                //stage.initStyle(StageStyle.TRANSPARENT); 
                Scene scene = new Scene(root); 
                stage.setScene(scene);

                stage.show();
    }

    @FXML
    private void excluirCurso(ActionEvent event) {
        Curso cur = tabela1.getSelectionModel().getSelectedItem();
            
            ConexaoDB con = new ConexaoDB();
            con.excluirCurso(cur);
            btnBuscar(event);
    }

    @FXML
    private void carregaUniversidade(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        String estado = cbEstados2.getValue();
        ObservableList<Universidade> oi = lista.universidades("","",estado,"");
        cbUniversidade2.setItems(oi);
    }

    @FXML
    private void btnCadastrar(ActionEvent event) {
        System.out.println("Chegou aqui! Curso:"+txtCurso.getText()+" Turno: "+cbTurno.getValue()+" universidade: "+cbUniversidade2.getValue());
        Universidade uni =  cbUniversidade2.getValue();
        Curso cur = new Curso(txtCurso.getText(), cbTurno.getValue(),uni);
        
        
        ConexaoDB con = new ConexaoDB();
        
        con.gravarCurso(cur);
        //con.gravarUniversidade(uni);
       // statusGravacao.setText(cur.getSigla()+"-"+cur.getNome()+" Cadastrada com SUCESSO!");
        
    }

    
}
