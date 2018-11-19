/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import conexao.ConexaoDB;
import conexao.ListagensDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import objetos.Cidade;
import objetos.Endereco;
import objetos.Estado;
import objetos.Universidade;


public class PopupController implements Initializable {
    
    public Stage tela;
    
    @FXML
    private JFXTextField txtSigla;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXComboBox<?> cbEstados;
    @FXML
    private JFXComboBox<?> cbCidade;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtRua;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtComplemento;
    
    public void alteraLabel(String i){
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       Universidade uni = Passagens.uni;
       ListagensDB lista = new ListagensDB();
       cbEstados.setItems(lista.estados());
       
       txtSigla.setText(uni.getSigla());
       txtNome.setText(uni.getNome());
       txtBairro.setText(uni.getBairro());
       txtRua.setText(uni.getRua());
       txtNumero.setText(uni.getNumero());
       txtComplemento.setText(uni.getComplemento());
       
       cbEstados.promptTextProperty().setValue(uni.getEstado());
       cbCidade.promptTextProperty().setValue(uni.getCidade());
       
       
    }    

    @FXML
    private void carregaCidades(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbCidade.setItems(lista.cidades((String) cbEstados.getValue()));
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        Universidade uni = Passagens.uni;
        
        uni.setSigla(txtSigla.getText());
        uni.setNome(txtNome.getText());
        uni.getEndereco().setRua(txtRua.getText());
        uni.getEndereco().setBairro(txtBairro.getText());
        uni.getEndereco().setNumero(txtNumero.getText());
        uni.getEndereco().setComplemento(txtComplemento.getText());
        if(cbCidade.getValue()!=null){
            uni.getEndereco().getCidade().setNome((String) cbCidade.getValue());
        }
        
        /*
        Estado estado = new Estado(" ", (String) cbEstados.getValue());
        Cidade cidade = new Cidade((String) cbCidade.getValue(),estado);
        Endereco endereco = new Endereco(txtRua.getText(),txtBairro.getText(),txtNumero.getText(),txtComplemento.getText(),cidade);
        Universidade universidade = new Universidade(txtSigla.getText(),txtNome.getText(),endereco);
        */
        
        ConexaoDB con = new ConexaoDB();
        
        //con.gravarUniversidade(uni);
        con.atualizarUniversidade(uni);
        
        System.out.println("UNIVERSIDADE ID: "+uni.getId()+" ENDERECO ID: "+uni.getEndereco().getId());
        Passagens.stage.close();
    }
    
}
