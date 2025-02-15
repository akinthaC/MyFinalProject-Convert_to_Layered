package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AccessoriesBo;
import lk.ijse.BO.custom.SupAccBo;
import lk.ijse.BO.custom.SupplierBo;
import lk.ijse.BO.custom.impl.AccessoriesBoImpl;
import lk.ijse.BO.custom.impl.SupAccBoImpl;
import lk.ijse.BO.custom.impl.SupplierBOImpl;
import lk.ijse.dto.AccessoriesDTO;
import lk.ijse.dto.SupAccDTO;
import lk.ijse.dto.SupplierDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.model.tm.SupAccTm;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddNewQtyAccessoriesController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbAccId;

    @FXML
    private JFXComboBox<String> cmbSup;

    @FXML
    private TableColumn<?, ?> colAccId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colpurchasedAmount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblAccId;

    @FXML
    private Label lblSup;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<SupAccTm> tblAccSupFIsh;

    @FXML
    private TextField txtQtyy;

    @FXML
    private TextField txtpurchasedAmount;

    public String SupId;
    SupAccBo supAccBo = (SupAccBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPACC);
    AccessoriesBo accessoriesBo = (AccessoriesBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.ACCESSORIES);
    SupplierBo supplierBo = (SupplierBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPPLIER);

    public void initialize() throws IOException, SQLException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllSupFIsh();
        getAccId();
        cmbSup.setEditable(true);

    }

    private void setCellValueFactory() {
        colAccId.setCellValueFactory(new PropertyValueFactory<>("AccId"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colpurchasedAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));




    }
    private void loadAllSupFIsh() throws SQLException {
        ObservableList<SupAccTm> obList2 = FXCollections.observableArrayList();

        try {


            List<SupAccDTO> supAcc = supAccBo.getAll();
            for (SupAccDTO supAcc1 : supAcc) {

                SupAccTm tm1 = new SupAccTm(
                        supAcc1.getAccId(),
                        supAcc1.getSupId(),
                        supAcc1.getDate(),
                        supAcc1.getQty(),
                        supAcc1.getAmount()
                );
                obList2.add(tm1);

            }
            tblAccSupFIsh.setItems(obList2);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    }



    private void getAccId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = supAccBo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

             cmbAccId.setItems(obList);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void QtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.utill.TextField.QTY,txtQtyy);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtpurchasedAmount.setText("");
        cmbAccId.getSelectionModel().clearSelection();
        txtQtyy.setText("");
        lblSup.setText("");
        lblAccId.setText("");
        cmbSup.getSelectionModel().clearSelection();



    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String supId= SupId;
        String accID= (String) cmbAccId.getValue();
        int qty = Integer.parseInt(txtQtyy.getText());
        Date date = Date.valueOf(LocalDate.now());
        System.out.println(accID + qty+supId);
        double amount= Double.parseDouble(txtpurchasedAmount.getText());

        try {
            if(supId.isEmpty() || accID.isEmpty()   ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

        SupAccDTO supAcc = new SupAccDTO(accID, supId, date, qty,amount);

        try {
            boolean isUpdate = accessoriesBo.updateSupAcc(qty, accID);
            if (isUpdate) {
                boolean isSaved = supAccBo.save(supAcc);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Qty Is Updated").show();
                    loadAllSupFIsh();
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbAccIdOnAction(ActionEvent event) {
        String id = cmbAccId.getValue();
        try {
            AccessoriesDTO accessories = accessoriesBo.searchById(id);

            if (accessories != null) {

                lblAccId.setText(accessories.getName());

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbSupOnAction(ActionEvent event) {

    }

    public void SupplierNICOnKeyReleased(KeyEvent keyEvent) {
        ObservableList<String> filterData = FXCollections.observableArrayList();
        String enterText = cmbSup.getEditor().getText();

        try {

            List<String> idList = supplierBo.searchNIC();

            for (String NIC : idList){
                if (NIC.contains(enterText)){
                    filterData.add(NIC);
                }
            }
            cmbSup.setItems(filterData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String NIC = cmbSup.getValue();
        try {
            SupplierDTO supplier = supplierBo.searchByNIC(NIC);
            if(supplier!=null) {
                lblSup.setText(supplier.getName());
                SupId=supplier.getId();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
