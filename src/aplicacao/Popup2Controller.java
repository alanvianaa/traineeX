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
import objetos.Curso;
import objetos.Universidade;

/**
 * FXML Controller class
 *
 * @author alan
 */
public class Popup2Controller implements Initializable {

    @FXML
    private JFXTextField txtCurso;
    @FXML
    private JFXComboBox<String> cbTurno;
    @FXML
    private JFXComboBox<String> cbEstados;
    @FXML
    private JFXComboBox<Universidade> cbUniversidade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       Curso cur = Passagens.cur;
       ListagensDB lista = new ListagensDB();
       cbEstados.setItems(lista.estados());
       
       txtCurso.setText(cur.getNome());
       
       ObservableList<String>turnos = FXCollections.observableArrayList();
       turnos.add("Manhã");
       turnos.add("Tarde");
       turnos.add("Noite");
       turnos.add("Integral");
       cbTurno.setItems(turnos);
       cbTurno.setValue(cur.getTurno());
       
       cbEstados.setValue(cur.getEstado());
       carregaUniversidade();
       System.out.println(" nome:  "+cur.getUniversidade());
       cbUniversidade.promptTextProperty().setValue(cur.getNomeUniversidade());

    }    

    @FXML
    private void carregaUniversidade() {
        ListagensDB lista = new ListagensDB();
        cbUniversidade.setItems(lista.universidades("","",cbEstados.getValue(),""));
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        Curso cur = Passagens.cur;
        cur.setNome(txtCurso.getText());
        cur.setTurno(cbTurno.getValue());
        if(cbUniversidade.getValue()!=null){
        cur.setUniversidade(cbUniversidade.getValue());
        }
        System.out.println("Uni::::::"+cbUniversidade.getValue());
        ConexaoDB con = new ConexaoDB();
        con.atualizarCurso(cur);
        
        Passagens.stage.close();
        
    }
    
}
