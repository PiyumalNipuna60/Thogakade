package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import util.CrudUtil;

import java.sql.*;

public class UpdateCustomerFormController {
    public Button btnUpdate;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerSalary;

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Customer c = new Customer(
                txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), Double.parseDouble(txtCustomerSalary.getText())
        );

// Use Statement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "UPDATE Customer SET name='" + c.getCusName() + "',address='" + c.getCusAddress() + "',salary='" + c.getCusSalary() + "' WHERE ID='" + c.getCusId() + "'";
            Statement sta = con.createStatement();
            if (sta.executeUpdate(sql) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

// Use PreparedStatement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "UPDATE Customer SET name=?,address=?,salary=? WHERE ID=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1,c.getCusName());
            stm.setObject(2,c.getCusAddress());
            stm.setObject(3,c.getCusSalary());
            stm.setObject(4,c.getCusId());

            if (stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use DBConnection==========================
/*        try {
            String sql = "UPDATE Customer SET name=?,address=?,salary=? WHERE ID=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setObject(1,c.getCusName());
            stm.setObject(2,c.getCusAddress());
            stm.setObject(3,c.getCusSalary());
            stm.setObject(4,c.getCusId());

            if (stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use DBConnection==========================
        try {
            if (CrudUtil.execute("UPDATE Customer SET name=?,address=?,salary=? WHERE ID=?",c.getCusName(),c.getCusAddress(),c.getCusSalary(),c.getCusId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }





    public void txtSearchOnAction(ActionEvent actionEvent) {

// Use Statement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "SELECT * FROM Customer WHERE id='" + txtCustomerId.getText() + "'";
            Statement sta = con.createStatement();
            ResultSet resultSet = sta.executeQuery(sql);
            if (resultSet.next()) {
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use PreparedStatement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "SELECT * FROM Customer WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1,txtCustomerId.getText());

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getString(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use DBConnection==========================
/*        try {
            String sql = "SELECT * FROM Customer WHERE id=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setObject(1,txtCustomerId.getText());

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getString(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/



// Use CrudUtil==========================
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE id=?",txtCustomerId.getText());
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getString(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
