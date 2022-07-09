package controller;

import db.DataSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveCustomerFormController {
    public Button btnSave;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerSalary;

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Customer c=new Customer(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText()));
        /*DataSet.customerArray.add(customer);
        if (DataSet.customerArray.add(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Customer!..").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went Wrong!..").show();
        }*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            String sql="INSERT INTO Customer VALUES('"+c.getCusId()+"','"+c.getCusName()+"','"+c.getCusAddress()+"','"+c.getCusSalary()+"')";
            Statement sta = con.createStatement();
            if (sta.executeUpdate(sql)>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Save Customer!..").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went Wrong!..").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


}
