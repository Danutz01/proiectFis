package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField name;

    @FXML
    private TextField pass;

    @FXML
    private ChoiceBox role;

    public void initialize() {
        role.getItems().addAll("Student", "Teacher");
    }

}
