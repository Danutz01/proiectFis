package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.loose.fis.sre.model.Course;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

public class UserPageController {

    @FXML
    private TableColumn<Course, String> Id;

    @FXML
    private TableColumn<Course, String> Name;

    @FXML
    private TableView<Course> Table;

    @FXML
    private TableColumn<Course, String> Teacher;

    @FXML
    private Button btnSearch;

    @FXML
    private ImageView img;

    @FXML
    private Label lblsearch;

    @FXML
    private Button show;
    @FXML
    private TextField searchtxt;

    @FXML
    private TextArea studentId;
    public ObservableList<Course> list = FXCollections.observableArrayList();
    public void initialize(){
        studentId.setText(LoginController.currentUser.getUsername());
        lblsearch.setVisible(false);


        for(User u : UserService.getDatabase().find()){

            if(u.getRole().equals("Teacher")){
                for(int i =0;i<u.contor;i++){
                    list.add(u.curs[i]);}
            }

        }
        Course a = new Course("123","mate","Liviu Cadariu") ;
        list.add(a);
        Table.setItems(list);
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Teacher.setCellValueFactory(new PropertyValueFactory<>("prof"));
    }


}

