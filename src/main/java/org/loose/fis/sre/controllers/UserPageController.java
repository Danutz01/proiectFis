package org.loose.fis.sre.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;

public class UserPageController {
    private int valid=1;
    @FXML
    private ListView<String> listview = new ListView();
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
    private Label dlbl;
    @FXML
    private Label lblsearch;

    @FXML
    private Button show;
    @FXML
    private Button download;
    @FXML
    private TextField searchtxt;

    @FXML
    private TextArea studentId;

    @FXML
    private Label usernameLabel;

    public ObservableList<Course> list = FXCollections.observableArrayList();
    public ObservableList<Course> listofcourses = FXCollections.observableArrayList();
    private ObjectRepository<User> users = UserService.getDatabase();
    private ObjectRepository<User> users2 = UserService.getDatabase();
    public void initialize() {
        usernameLabel.setText(LoginController.currentUser.getUsername());
        Id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        Teacher.setCellValueFactory(new PropertyValueFactory<Course, String>("prof"));
        Table.setVisible(false);
        dlbl.setVisible(false);
        listview.setVisible(false);
        Course c1 = new Course("10001","F.I.S.","Marinescu");
        Course c2 = new Course("10002","O.O.P.","Marinescu");
        Course c3 = new Course("10003","P.O.O.","Marinescu");
        listofcourses.add(c1);listofcourses.add(c2);listofcourses.add(c3);
    }
    public void search(){
        for(int i=0;i<listofcourses.size();i++) {
            Course c = listofcourses.get(i);
            list.add(c);
        }
        Table.setItems(list);
        Table.setVisible(true);
        listview.setVisible(false);
    }
   public void searchName(){
        String item = searchtxt.getText();
        if(!list.isEmpty()){
         for(int i=0;i<list.size();i++){
            Course c =list.get(i);
            if(item!=null && item.equals(c.getName())){
                String txt = c.toString();
                lblsearch.setText(txt);
                lblsearch.setTextFill(Color.web("#29b11a"));
            }
            if(item==null){
                lblsearch.setText("Empty text field!!!");
                lblsearch.setTextFill(Color.web("#861e0d"));
            }
            if(!item.equals(c.getName())){
                lblsearch.setText("Not find!!!");
                lblsearch.setTextFill(Color.web("#861e0d"));
            }
         }
        }else{
            lblsearch.setText("Not find!!!");
            lblsearch.setTextFill(Color.web("#861e0d"));
        }
   }
    public void Download(){
     Table.setVisible(true);
     listview.setVisible(false);
     Course curs = Table.getSelectionModel().getSelectedItem();
     LoginController.currentUser.downloadList.add(curs);
     dlbl.setVisible(true);
     String txt = "Downloaded "+ curs.toString();
     dlbl.setText(txt);
    }
    public void History(){
        listview.setVisible(true);
        if (valid%2==0){listview.setVisible(false);}
        listview.getItems().clear();
        for(int i = 0; i<LoginController.currentUser.downloadList.size();i++){
            String txt = "";
            if( LoginController.currentUser.downloadList.get(i)!=null){
                txt = txt + LoginController.currentUser.downloadList.get(i).toString();
            }
            listview.getItems().add(txt);
        }

        valid++;
    }
    public void Back(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("School App");
        stage.show();
    }

}
