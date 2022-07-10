package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomerFormController {
    public Button btnDelete;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;

    public void btnDeleteOnAction(ActionEvent actionEvent) {
 /*for (Customer temp : DataSet.customerArray) {
            if (temp.getCusId().equals(txtCustomerId.getText())) {
                DataSet.customerArray.remove(temp);
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
                return;
            }
        }*/

// Use Statement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "DELETE FROM Customer WHERE id='" + txtCustomerId.getText() + "'";
            Statement sta = con.createStatement();
            sta.executeQuery(sql);
            if (sta.executeUpdate(sql) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use PreparedStatement==========================
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "DELETE FROM Customer WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1,txtCustomerId.getText());
            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use DBConnection==========================
/*        try {
            String sql = "DELETE FROM Customer WHERE id=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setObject(1, txtCustomerId.getText());
            if (stm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use CrudUtil==========================
        try {
            if (CrudUtil.execute("DELETE FROM Customer WHERE id=?",txtCustomerId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void Search() {
/*        for (Customer temp : DataSet.customerArray) {
            if (temp.getCusId().equals(txtCustomerId.getText())) {
                txtCustomerName.setText(temp.getCusName());
                txtCustomerAddress.setText(temp.getCusAddress());
                txtCustomerSalary.setText(String.valueOf(temp.getCusSalary()));
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();*/


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
    /*    try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "SELECT * FROM Customer WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1,txtCustomerId.getText());

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
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
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use CrudUtil==========================
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", txtCustomerId.getText());
            if (resultSet.next()) {
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
