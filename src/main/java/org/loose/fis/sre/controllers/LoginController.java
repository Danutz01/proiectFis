package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lbl;
    @FXML
    private TextField name;

    @FXML
    private TextField pass;

    @FXML
    private ChoiceBox role;
    public ObjectRepository<User> user;
    public User currentUser;

    public void initialize() {
        role.getItems().addAll("Student", "Teacher");
    }
    public void login(ActionEvent event) throws IOException {
        user = UserService.getDatabase();
        for(User u : user.find() ){
            if(name.getText().equals(u.getUsername()) &&  UserService.encodePassword(name.getText(),pass.getText()).equals(pass.getText())){
             currentUser = u;
             Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UserPage.fxml"));
              Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
              Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.setTitle("School App");
              stage.show();
            }
            else{
                lbl.setText(" Invalid credentials !!! ");
            }
        }
    }

}
