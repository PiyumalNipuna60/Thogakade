package controller;

import db.DataSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

public class DeleteCustomerFormController {
    public Button btnDelete;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        for (Customer temp : DataSet.customerArray) {
            if (temp.getCusId().equals(txtCustomerId.getText())) {
                DataSet.customerArray.remove(temp);
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
                return;
            }
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void Search() {
        for (Customer temp : DataSet.customerArray) {
            if (temp.getCusId().equals(txtCustomerId.getText())) {
                txtCustomerName.setText(temp.getCusName());
                txtCustomerAddress.setText(temp.getCusAddress());
                txtCustomerSalary.setText(String.valueOf(temp.getCusSalary()));
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
    }
}
