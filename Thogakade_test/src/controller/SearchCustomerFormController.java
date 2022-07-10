package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class SearchCustomerFormController {
    public Button btnSearch;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void Search(){
        /*for (Customer temp: DataSet.customerArray) {
            if (temp.getCusId().equals(txtCustomerId.getText())){
                txtCustomerName.setText(temp.getCusName());
                txtCustomerAddress.setText(temp.getCusAddress());
                txtCustomerSalary.setText(String.valueOf(temp.getCusSalary()));
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING,"Empty Result!..").show();*/

// Use Statement==========================
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "SELECT * FROM Customer WHERE id='" + txtCustomerId.getText() + "'";
            Statement sta = con.createStatement();
            ResultSet resultSet = sta.executeQuery(sql);
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


// Use Statement==========================
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            String sql="SELECT * FROM Customer WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1,txtCustomerId.getText());

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

}
