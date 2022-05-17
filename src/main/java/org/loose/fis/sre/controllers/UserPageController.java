package org.loose.fis.sre.controllers;
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

    public void initialize() {
        studentId.setText(LoginController.currentUser.getUsername());
        lblsearch.setVisible(false);

        Id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        Teacher.setCellValueFactory(new PropertyValueFactory<Course, String>("prof"));
        Table.setItems(list);
    }
    public void search(){
        /*for(User u : UserService.getDatabase().find()){

            if(u.getRole().equals("Teacher")){
                for(int i =0;i<u.contor;i++){
                    list.add(u.curs[i]);}
            }

        }*/
        Course a = new Course("123","mate","Liviu Cadariu") ;
        list.add(a);
    }
   public void searchName(){
        String item = searchtxt.getText();
        ObjectRepository<User> user = UserService.getDatabase();
        for(User u : user.find()) {
          if(u.getRole().equals("Teacher")) {
              for (int i = 0; i < u.contor; i++) {
                  if (item.equals(u.curs[i].name)) {
                      lblsearch.setText(u.curs[i].id + " " + u.curs[i].name + " " + u.curs[i].prof);
                      lblsearch.setVisible(true);
                 }
             }
         }

       }
   }

}

