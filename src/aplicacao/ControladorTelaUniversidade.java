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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objetos.*;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class ControladorTelaUniversidade implements Initializable {

    @FXML
    private JFXTextField txtSigla;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXComboBox<String> cbEstados;
    @FXML
    private JFXComboBox<String> cbCidade;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtRua;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtComplemento;
    @FXML
    private Label label1;
    @FXML
    private JFXComboBox<String> cbEstados2;
    @FXML
    private JFXComboBox<String> cbCidade2;
    //-----------------TABELA UNIVERSIDADES CADASTRADAS ----------------------------------------
    @FXML
    private TableView<Universidade> tabela1;
    @FXML
    private TableColumn<Universidade, String> col_id;
    @FXML
    private TableColumn<Universidade, String> col_sigla;
    @FXML
    private TableColumn<Universidade, String> col_nome;
     @FXML
    private TableColumn<Universidade, String> col_estado;
    @FXML
    private TableColumn<Universidade, String> col_cidade;
    @FXML
    private TableColumn<Universidade, String> col_bairro;
    @FXML
    private TableColumn<Universidade, String> col_rua;
    @FXML
    private TableColumn<Universidade, String> col_numero;
    @FXML
    private TableColumn<Universidade, String> col_complemento;
        
    
    //-----------------TABELA UNIVERSIDADES CADASTRADAS ----------------------------------------
    @FXML
    private Label statusGravacao;
    @FXML
    private JFXTextField txtbuscaSigla;
    @FXML
    private JFXTextField txtBuscaNome;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExcluir;
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListagensDB lista = new ListagensDB();
        
        cbEstados.setItems(lista.estados());

        cbEstados2.setItems(lista.estados());
        
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    
    @FXML
    private void carregaCidades(){
        ListagensDB lista = new ListagensDB();
        cbCidade.setItems(lista.cidades(cbEstados.getValue()));
        
        cbCidade2.setItems(lista.cidades(cbEstados2.getValue()));
    }
    
    @FXML
    private void btnCadastrar(ActionEvent event) {
        
        Estado estado = new Estado(" ",cbEstados2.getValue());
        Cidade cidade = new Cidade(cbCidade2.getValue(),estado);
        Endereco endereco = new Endereco(txtRua.getText(),txtBairro.getText(),txtNumero.getText(),txtComplemento.getText(),cidade);
        Universidade uni = new Universidade(txtSigla.getText(),txtNome.getText(),endereco);
        
        ConexaoDB con = new ConexaoDB();
        
        //con.gravarUniversidade(uni);
        con.gravarUniversidade(uni);
        statusGravacao.setText(uni.toString());
        
        
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        
        ListagensDB lista = new ListagensDB();  
  
        tabela1.setItems(lista.universidades(txtbuscaSigla.getText(),txtBuscaNome.getText(),cbEstados.getValue()));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_sigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //col_nome.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col_cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        col_bairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        col_rua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        col_complemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        
       
        
    }

    @FXML
    private void ativaEdicao(){
        
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @FXML
    private void editarUniversidade(ActionEvent event) throws IOException{
        

      
            
            String i = tabela1.getSelectionModel().getSelectedItem().getSigla();
            //col_sigla.setCellFactory(TextFieldTableCell.forTableColumn());
            //System.out.println("Hello!->> "+i);
            
            //PopupController editar = new PopupController();
            //editar.alteraLabel(i);
            
   
          

                 
                Stage stage = new Stage(); 
                //stage.initStyle(StageStyle.TRANSPARENT);
                Parent root = FXMLLoader.load(getClass().getResource("/aplicacao/Popup.fxml")); 
                
                //stage.initStyle(StageStyle.TRANSPARENT); 
                
                Scene scene = new Scene(root); 
                stage.setScene(scene);
                stage.show();
                
        
    }

    @FXML
    private void excluirUniversidade(ActionEvent event) {
        
    }
}
