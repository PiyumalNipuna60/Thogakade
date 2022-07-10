package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveCustomerFormController {
    public Button btnSave;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerSalary;

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Customer c = new Customer(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), Double.parseDouble(txtCustomerSalary.getText()));
        /*DataSet.customerArray.add(customer);
        if (DataSet.customerArray.add(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Customer!..").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went Wrong!..").show();
        }*/

// Use Statement==========================
/*        try {
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
        }*/

// Use PreparedStatement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "INSERT INTO Customer VALUES(?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, c.getCusId());
            stm.setObject(2, c.getCusName());
            stm.setObject(3, c.getCusAddress());
            stm.setObject(4, c.getCusSalary());
            if (stm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer!..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went Wrong!..").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use DBConnection==========================
/*        try {
            String sql = "INSERT INTO Customer VALUES(?,?,?,?)";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setObject(1, c.getCusId());
            stm.setObject(2, c.getCusName());
            stm.setObject(3, c.getCusAddress());
            stm.setObject(4, c.getCusSalary());
            if (stm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer!..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use CrudUtil==========================
        try {
            if (CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",c.getCusId(),c.getCusName(),c.getCusAddress(),c.getCusSalary())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer!..").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something went Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
