package org.loose.fis.sre.controllers;
import javafx.scene.paint.Color;
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
    private Button download;
    @FXML
    private TextField searchtxt;

    @FXML
    private TextArea studentId;

    @FXML
    private Label usernameLabel;

    public ObservableList<Course> list = FXCollections.observableArrayList();
    private ObjectRepository<User> users = UserService.getDatabase();
    private ObjectRepository<User> users2 = UserService.getDatabase();
    public void initialize() {
        usernameLabel.setText(LoginController.currentUser.getUsername());
        Id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        Teacher.setCellValueFactory(new PropertyValueFactory<Course, String>("prof"));
        Table.setVisible(false);
    }
    public void search(){
        for(User u : users.find()) {
            if ("Teacher".equals(u.getRole())) {
                Course a = new Course("1234", "fizica", "Liviu Cadariu");
                u.curs[0] = a;
                u.contor++;
                for (int i = 0; i < u.contor; i++) {
                    list.add(u.curs[i]);
                }
            }
        }
        Table.setItems(list);
        Table.setVisible(true);
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
     search();
     Course curs = Table.getSelectionModel().getSelectedItem();
     //LoginController.currentUser.downloadList.add(curs);

    }

}
