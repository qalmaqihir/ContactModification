package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class Controller {
    @FXML    private ListView<Student> studentsListView;
    @FXML    private ImageView avatarImageView;
    @FXML    private TextField nameTextField;
    @FXML    private TextField secondNameTextField;
    @FXML    private TextField emailTextFiled;
    @FXML    private TextField phoneTextField;
    @FXML    private TextField majorTextField;
    @FXML    private Button addButton;
    @FXML    private Button deleteButton;
    @FXML    private Button clearButton;
    @FXML    private TextField imagePathTextField;


    private final ObservableList<Student> students = FXCollections.observableArrayList();

    public void initialize(){
        students.add(new Student("Jawad", "Haider", "jh@gmail.com", "+1234565443","CS", "sample/img/1.jpeg"));
        students.add(new Student("Yasir", "Nawaz", "yn@yahoo.com", "+9234590069554","CS", "sample/img/2.jpeg"));
        students.add(new Student("Aqila", "Goof", "Aql@hotmial.com", "+9234569554","CM", "sample/img/3.png"));


        //Collections.sort(students);
        students.stream().sorted().map((s -> s.getLastName()));

        studentsListView.setItems(students);

        // when ListView selection changes, show large cover in ImageView
        studentsListView.getSelectionModel().selectedItemProperty().
                addListener(
                        new ChangeListener<Student>() {
                            @Override
                            public void changed(ObservableValue<? extends Student> ov, Student oldValue, Student t1) {
                                avatarImageView.setImage(new Image(t1.getImage()));
                                nameTextField.setText(t1.getName());
                                secondNameTextField.setText(t1.getLastName());
                                emailTextFiled.setText(t1.getEmail());
                                phoneTextField.setText(t1.getPhone());
                                majorTextField.setText(t1.getMajor());

                            }
                        }
                );

        // set custom ListView cell factory
        studentsListView.setCellFactory(
                new Callback<ListView<Student>, ListCell<Student>>() {
                    @Override
                    public ListCell<Student> call(ListView<Student> listView) {
                        return new ImageBox();
                    }
                }
        );



    }



    @FXML    void onAddPressed(ActionEvent event) {
        students.add(new Student(nameTextField.getText(), secondNameTextField.getText(), emailTextFiled.getText(),phoneTextField.getText(),majorTextField.getText(), imagePathTextField.getText()));
        studentsListView.setItems(students);
    }

    @FXML
    void onCLearPressed(ActionEvent event) {
        nameTextField.setText("");
        secondNameTextField.setText("");
        emailTextFiled.setText("");
        phoneTextField.setText("");
        majorTextField.setText("");
        imagePathTextField.setText("");

    }

    @FXML
    void onDeletePressed(ActionEvent event) {
        try {
            Student newOne = new Student(nameTextField.getText(), secondNameTextField.getText(),
                    emailTextFiled.getText(),phoneTextField.getText(),majorTextField.getText(),imagePathTextField.getText() );

            students.removeIf(i-> i.getName().equals(newOne.getName()) && i.getLastName().equals(newOne.getLastName()) && i.getEmail().equals(newOne.getEmail()) && i.getPhone().equals(newOne.getPhone()) && i.getMajor().equals(newOne.getMajor()) && i.getImage().equals(newOne.getImage()));
            studentsListView.setItems(students);
            nameTextField.setText("");
            secondNameTextField.setText("");
            emailTextFiled.setText("");
            phoneTextField.setText("");
            majorTextField.setText("");
            imagePathTextField.setText("");


        }
        catch (NumberFormatException ex) {
            nameTextField.setText("did not find in the directory");
            secondNameTextField.setText("did not find in the directory");

        }

    }

}
