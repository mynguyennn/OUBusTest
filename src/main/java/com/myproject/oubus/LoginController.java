/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.HashUtils;
import com.myproject.conf.Utils;
import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.Account;
import com.myproject.services.AccountService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;


/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class LoginController implements Initializable  {
    @FXML private TextField txtTaiKhoan;
    @FXML private PasswordField txtMatKhau;
    public static int mnv;
    private static final AccountService acService = new AccountService();
    
    /**
     * Initializes the controller class.
     */


    public void loginHandler(ActionEvent event) throws IOException, SQLException {
        if (this.txtTaiKhoan.getText().isEmpty() || this.txtMatKhau.getText().isEmpty()){
             Utils.getBox("Vui lòng nhập tài khoản, mật khẩu.", Alert.AlertType.WARNING).show();
        } else{
            try {           
                    Account a = acService.getAccount(txtTaiKhoan.getText());             
                    if (a != null) {                      
                        if (a.getMatKhau().equals(txtMatKhau.getText())) {
                            if (a.getMaQuyen()== 2 ){
                                FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("MainStaffScreen.fxml"));
                                Scene scene = new Scene(fxmloader.load());
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                this.mnv = a.getId();
                                Button btn = (Button) event.getSource();
                                Stage stagelogin = (Stage) btn.getScene().getWindow();
                                stagelogin.close();
                                
                            }
                            else {
                                FXMLLoader fxmloader2 = new FXMLLoader(App.class.getResource("ListTourAdmin.fxml"));

                                Scene scene = new Scene(fxmloader2.load());
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle("OuBus");
                                stage.show();
                                this.mnv = a.getId();
                                Button btn = (Button) event.getSource();
                                Stage stagelogin = (Stage) btn.getScene().getWindow();
                                stagelogin.close();
                            }
                            
                        }
                        else{
                            Utils.getBox("Mật khẩu không chính xác", Alert.AlertType.WARNING).show();
                                }
                    } 
                    else
                        Utils.getBox("Tài khoản không tồn tại", Alert.AlertType.WARNING).show();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    public void ActionClose() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
