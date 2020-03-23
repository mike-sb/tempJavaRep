package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public ComboBox cmbBxCountries;
    @FXML
    TextField txtBxCountPeople;
    @FXML
    TextField txtBxCountDays;
    @FXML
    TextField txtBxCost;

    public void CalcCost(ActionEvent event)
    {
        Country country = (Country) cmbBxCountries.getSelectionModel().getSelectedItem();

        int countPeople = Integer.parseInt(txtBxCountPeople.getText());
        int countDays = Integer.parseInt(txtBxCountDays.getText());

        int cost = countDays * countPeople * country.getCost();

        txtBxCost.setText(String.valueOf(cost));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Египет", 3000));
        countries.add(new Country("Тайланд", 4000));
        countries.add(new Country("Индия", 5000));
        countries.add(new Country("Турция", 2000));


        ObservableList<Country> list = FXCollections.observableArrayList(countries);
        cmbBxCountries.setItems(list);
        cmbBxCountries.setValue(countries.get(2));
    }
}
