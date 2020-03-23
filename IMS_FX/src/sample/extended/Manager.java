package sample.extended;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;

public class Manager {

    private static Manager instance;

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public Manager() {

    }

    public Optional<ButtonType> makeAlert(String str)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Warning");
        alert.setHeaderText(str);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

}
