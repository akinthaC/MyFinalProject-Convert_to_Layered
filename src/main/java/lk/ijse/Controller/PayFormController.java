package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.OrderBo;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.dto.PaymentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PayFormController {

    @FXML
    private Label LblDate;

    @FXML
    private Label LblOrd;

    @FXML
    private Label LblTotal;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtPayId;
    @FXML
    private TextField TxtAdvance;
    PaymentBO paymentBo = (PaymentBO) BOFactory.getBoFactory().GetBo(BOFactory.BOType.PAYMENT);
    OrderBo orderBo = (OrderBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.ORDER);

    public void initialize() throws IOException {
        setDate();
        setTime();
        getCurrentOrderId();
        getPayType();
        LblTotal.setText(String.valueOf(OrderFormController.total11));
        LblOrd.setText(OrderFormController.orderId1);



    }
    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(timeFormatter);

            lblTime.setText(formattedTime);
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);

        clock.play();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
        LblDate.setText(String.valueOf(now));

    }

    private void getCurrentOrderId() {
        try {
            String currentId = paymentBo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtPayId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[pP]+");

            int idNum = Integer.parseInt(split[1]);

            return "P" + String.format("%03d", ++idNum);

        }

        return "P001";
    }


    private void getPayType() {
        ObservableList<String> obList = FXCollections.observableArrayList();


        List<String> idList = paymentBo.getType();

        for(String value : idList) {
            obList.add(value);
        }

        cmbType.setItems(obList);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtPayId.getText();
        String orderId1 = OrderFormController.orderId1;
        double total11 = OrderFormController.total11;
        String date = LblDate.getText();

        double  advance = Double.parseDouble(TxtAdvance.getText());
        String type = (String) cmbType.getValue();
        double AmountToPaid = total11-advance;
        String Status;


        if (AmountToPaid == 0){
            Status ="Successes";
        }else {
            Status="Pending";
        }

        try {
            if(id.isEmpty() || orderId1.isEmpty() || date.isEmpty() || type.isEmpty()  ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        PaymentDTO payment = new PaymentDTO(id, orderId1, date, total11, advance, type,AmountToPaid,Status);


        try {
            boolean isSaved = paymentBo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment Successfully !!!.").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {

    }

}
