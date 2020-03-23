package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


//--module-path "C:\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml
public class Controller implements Initializable {
    public TableColumn columnName;
    public TableColumn columnAge;
    public TextField txtBxName;
    public TextField txtBxAge;
    public TableView table;

    ObservableList<Person> personList;

    public void AddHuman(ActionEvent event)
    {
        String name = txtBxName.getText();
        int age = Integer.parseInt(txtBxAge.getText());

        Person p = new Person(name, age);
        personList.add(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personList = FXCollections.observableArrayList();

        table.setItems(personList);

        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
    }

    public void ShowView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPersonView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddPersonController controller = fxmlLoader.<AddPersonController>getController();
       controller.init(personList);

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        ////Alert Controller

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Delete");
        alert.setHeaderText("Confirm?");
        alert.setContentText("Are you sure you want to delete");
        Optional<ButtonType> result = alert.showAndWait();
    }
}
