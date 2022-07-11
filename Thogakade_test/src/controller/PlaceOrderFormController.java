package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;

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
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotalCost;
    public TableColumn colButton;
    public Label lblTotal;

    public void initialize() {

        LoadDateAndTime();

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

    public void CustomerAddOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrder(ActionEvent actionEvent) {
    }

    public void addtoCartOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
    }
}
