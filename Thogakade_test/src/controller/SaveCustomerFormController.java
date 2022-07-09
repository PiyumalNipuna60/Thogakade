package controller;

import db.DataSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

public class SaveCustomerFormController {
    public Button btnSave;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerSalary;

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Customer customer=new Customer(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText()));
        DataSet.customerArray.add(customer);
        if (DataSet.customerArray.add(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Customer!..").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went Wrong!..").show();
        }
    }


}
