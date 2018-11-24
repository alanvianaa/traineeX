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
import objetos.Aluno;
import objetos.Curso;
import objetos.Estado;
import objetos.Universidade;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class ControladorTelaAluno implements Initializable {

    @FXML
    private JFXTextField txtbuscaNome;
    @FXML
    private JFXTextField txtBuscaCPF;
    @FXML
    private JFXComboBox<String> cbEstados;
    @FXML
    private JFXComboBox<Universidade> cbUniversidades;
    @FXML
    private JFXComboBox<Curso> cbCursos;
    @FXML
    private JFXComboBox<String> cbAno;
    @FXML
    private TableView<Aluno> tabela1;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nome;
    @FXML
    private TableColumn<?, ?> col_CPF;
    @FXML
    private TableColumn<?, ?> col_curso;
    @FXML
    private TableColumn<?, ?> col_turno;
    @FXML
    private TableColumn<?, ?> col_turma;
    @FXML
    private TableColumn<?, ?> col_universidade;
    @FXML
    private TableColumn<?, ?> col_Estados;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXComboBox<String> cbEstados2;
    @FXML
    private JFXComboBox<Universidade> cbUniversidades2;
    @FXML
    private JFXComboBox<Curso> cbCursos2;
    @FXML
    private JFXComboBox<String> cbAno2;
    @FXML
    private Label statusGravacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListagensDB lista = new ListagensDB();
        
        cbEstados.setItems(lista.estados());

        cbEstados2.setItems(lista.estados());
        
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        
        ObservableList<String>ano = FXCollections.observableArrayList();
        ano.add("2014");
        ano.add("2015");
        ano.add("2016");
        ano.add("2017");
        ano.add("2018");
       
        
        cbAno.setItems(ano);
        cbAno2.setItems(ano);
    }    

    @FXML
    private void carregaUniversidades(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbUniversidades.setItems(lista.universidades("","",cbEstados.getValue(), ""));
        cbUniversidades2.setItems(lista.universidades("","",cbEstados2.getValue(), ""));
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        
        ListagensDB lista = new ListagensDB();  
  
        tabela1.setItems(lista.alunos(txtbuscaNome.getText(),txtBuscaCPF.getText(),cbUniversidades.getValue(),cbCursos.getValue(),cbEstados.getValue(),cbAno.getValue()));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_CPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        col_universidade.setCellValueFactory(new PropertyValueFactory<>("universidade"));
        //col_nome.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_curso.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        col_turno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        col_turma.setCellValueFactory(new PropertyValueFactory<>("ano_ingresso"));
        col_Estados.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
    }

    @FXML
    private void ativaEdicao(MouseEvent event) {
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @FXML
    private void editarAluno(ActionEvent event) throws IOException {
        Aluno aluno = tabela1.getSelectionModel().getSelectedItem();
            
            Passagens.aluno = aluno;
                Stage stage = new Stage();
               
                Parent root = FXMLLoader.load(getClass().getResource("/aplicacao/Popup3.fxml")); 
                Passagens.stage = stage;      
                //stage.initStyle(StageStyle.TRANSPARENT); 
                Scene scene = new Scene(root); 
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void excluirAluno(ActionEvent event) {
        Aluno aluno = (Aluno) tabela1.getSelectionModel().getSelectedItem();
            ConexaoDB con = new ConexaoDB();
            con.excluirAluno(aluno);
            btnBuscar(event);
    }

    @FXML
    private void btnCadastrar(ActionEvent event) {
        int ano = Integer.valueOf(cbAno2.getValue());
        
        Aluno aluno = new Aluno(txtNome.getText(), txtCPF.getText(),cbCursos2.getValue(),ano);
        System.out.println(" "+aluno);
        ConexaoDB con = new ConexaoDB();
        
        con.gravarAluno(aluno);
        //con.gravarUniversidade(uni);
        statusGravacao.setText(aluno.toString());
    }

    @FXML
    private void carregaCursos(ActionEvent event) {
        
        ListagensDB lista = new ListagensDB();
        
        cbCursos.setItems(lista.cursos("",null,null,cbUniversidades.getValue()));
        cbCursos2.setItems(lista.cursos("",null,null,cbUniversidades2.getValue()));
        
    }
    
}
