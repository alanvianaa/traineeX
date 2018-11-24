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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import objetos.Aluno;
import objetos.Curso;
import objetos.Estado;
import objetos.Universidade;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class Popup3Controller implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXComboBox<String> cbEstados;
    @FXML
    private JFXComboBox<Universidade> cbUniversidades;
    @FXML
    private JFXComboBox<Curso> cbCursos;
    @FXML
    private JFXComboBox<Integer> cbAno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Aluno aluno = Passagens.aluno;
       ListagensDB lista = new ListagensDB();
       
       ObservableList<Integer>ano = FXCollections.observableArrayList();
        ano.add(2014);
        ano.add(2015);
        ano.add(2016);
        ano.add(2017);
        ano.add(2018);
       
       txtNome.setText(aluno.getNome());
       txtCPF.setText(aluno.getCPF());
       cbEstados.setItems(lista.estados());
       cbEstados.setValue(aluno.getEstado());
       
       cbUniversidades.setItems(lista.universidades("","",cbEstados.getValue(),""));
       cbUniversidades.promptTextProperty().setValue(aluno.getCurso().getNomeUniversidade());
       
       cbCursos.promptTextProperty().setValue(aluno.getCurso().toString());
      
       cbAno.setItems(ano);
       cbAno.setValue(aluno.getAno_ingresso());
    }    

    @FXML
    private void carregaUniversidades(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbUniversidades.setItems(lista.universidades("","",cbEstados.getValue(),""));
    }

    @FXML
    private void carregaCursos(ActionEvent event) {
        ListagensDB lista = new ListagensDB();
        cbCursos.setItems(lista.cursos("", null, null, cbUniversidades.getValue()));
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        Aluno aluno = Passagens.aluno;
        aluno.setAno_ingresso(cbAno.getValue());
        aluno.setCPF(txtCPF.getText());
        aluno.setNome(txtNome.getText());
        
        if(cbCursos.getValue()!=null){
            aluno.setCurso(cbCursos.getValue());
        }
        
        System.out.println("++++++++++"+Passagens.aluno);
        
        ConexaoDB con = new ConexaoDB();
        con.atualizarAluno(aluno);
        
        Passagens.stage.close();
        
    }
    
}
