package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField parola;

    @FXML
    private ChoiceBox role;
    public ObjectRepository<User> user = UserService.getDatabase();
    public static User currentUser;
    public int valid =0;
    public void initialize() {
        role.getItems().addAll("Student", "Teacher");
    }
    public void Login(ActionEvent event) throws IOException {
        String nn = name.getText();
        String p = parola.getText();
        for(User u : user.find() ){

            if(nn.equals(u.getUsername()) && UserService.encodePassword(nn,p).equals(u.getPassword())){
              currentUser = u;
              Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StudentPage.fxml"));
              Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
              Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.setTitle("School App");
              stage.show();
              valid = 1;
            }
        }
        if (valid == 0){
            lbl.setText(" Invalid credentials !!!");
        }
    }

}
