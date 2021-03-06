package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import views.TM.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class PlaceOrderFormController {

    public Label lblDate;
    public Label lblTime;
    public ComboBox cmdCusId;
    public TextField txtName;
    public TextField txtxAddress;
    public TextField txtSalary;
    public ComboBox cmdItemCode;
    public TextField txtDescription;
    public TextField txtItemQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public Button btnRemoveItem;
    public TableView<CartTm> tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotalCost;
    public TableColumn colButton;
    public Label lblTotal;
    ObservableList<CartTm> obList=FXCollections.observableArrayList();

    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colButton.setCellValueFactory(new PropertyValueFactory<>("btn"));

        LoadDateAndTime();
        setCustomerId();
        setItemCode();

        cmdCusId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setCustomerDetails(newValue);
                });

        cmdItemCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setItemDetails(newValue);
                });
    }

    private void setItemDetails(Object newValue) {
        try {
            Item item = CrudController.getItem((String) newValue);
            if (item!=null){
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtItemQtyOnHand.setText(String.valueOf(item.getQty()));
            }else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetails(Object newValue) {
        try {
            Customer c = CrudController.getCustomer((String) newValue);
            if (c != null) {
                txtName.setText(c.getCusName());
                txtxAddress.setText(c.getCusAddress());
                txtSalary.setText(String.valueOf(c.getCusSalary()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result!..").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemCode() {
        try {
            ObservableList<String> obListItem = FXCollections.observableArrayList(
                    CrudController.getItemCode()
            );
            cmdItemCode.setItems(obListItem);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerId() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList(
                    CrudController.getCustomerId()
            );
            cmdCusId.setItems(obList);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void LoadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void CustomerAddOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/SaveCustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void btnPlaceOrder(ActionEvent actionEvent) {
    }

    public void addtoCartOnAction(ActionEvent actionEvent) {
        Double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());
        Double totalCost=unitPrice*qty;

        CartTm exits = isExits(cmdItemCode.getValue());
        if (exits!=null){
            for (CartTm temp:obList) {
                if (temp.equals(exits)){
                    temp.setQty(temp.getQty()+qty);
                    temp.setTotalCost(temp.getTotalCost()+totalCost);
                }
            }
        }else {
            Button btn=new Button("Delete");

            CartTm tm=new CartTm(
                    (String) cmdItemCode.getValue(),
                    txtDescription.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );
            btn.setOnAction(e ->{
                obList.remove(tm);
            });

            obList.add(tm);
            tblCart.setItems(obList);
        }
        tblCart.refresh();
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
    }

    private CartTm isExits(Object value){
        for (CartTm tm: obList) {
            if (tm.getCode().equals(value)){
                return tm;
            }
        }
        return null;
    }
}
