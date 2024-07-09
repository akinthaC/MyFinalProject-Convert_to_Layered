
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.impl.FishBoImpl;
import lk.ijse.BO.custom.impl.SupFishBoImpl;
import lk.ijse.BO.custom.impl.SupplierBOImpl;
import lk.ijse.Entity.Fish;
import lk.ijse.Entity.Supplier;
import lk.ijse.dto.FishDTO;
import lk.ijse.dto.SupFishDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.model.tm.FishTm;
import lk.ijse.model.tm.SupFishTm;
import lk.ijse.utill.Regex;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FishFormController {

    @FXML private TableColumn<?, ?> ColFishName;

    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXComboBox<String> cmbSup;
    @FXML
    private TableColumn<?, ?> colFishId2;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colFishId;
    @FXML
    private TableColumn<?, ?> colNoramalPrice;
    @FXML
    private TableColumn<?, ?> colQtyOnHand;
    @FXML
    private TableColumn<?, ?> colWholeSalePrice;
    @FXML
    private TableColumn<?, ?> colSupId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblSup;
    @FXML
    private TableView<FishTm> tblFish;
    @FXML
    private TableView<SupFishTm> tblAccSupFIsh;
    @FXML
    private TextField txtFishId;
    @FXML
    private TextField txtFishName;
    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TextField txtNormalPrice;
    @FXML
    private TextField txtWholeSalePrice;
    public AnchorPane rootNode;
    @FXML
    private TextField txtpurchasedAmount;

    FishBoImpl fishBo = (FishBoImpl) BOFactory.getBoFactory().GetBo(BOFactory.BOType.FISH);
    SupplierBOImpl supplierBo = (SupplierBOImpl) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPPLIER);
    SupFishBoImpl supFishBo = (SupFishBoImpl) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPFISH);


    public void initialize() throws IOException, SQLException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllFish();
        getCurrentOrderId();
        getSupllierId();


    }

    private void getSupllierId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = supplierBo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSup.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = fishBo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtFishId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[fF]+");

            int idNum = Integer.parseInt(split[1]);

            return "F" + String.format("%03d", ++idNum);

        }

        return "F001";
    }

    private void loadAllFish() throws SQLException {
        ObservableList<FishTm> obList = FXCollections.observableArrayList();
        ObservableList<SupFishTm> obList2 = FXCollections.observableArrayList();


        try {
            List<FishDTO> fishList = fishBo.getAll();
            for (FishDTO fish : fishList) {

                FishTm tm = new FishTm(
                        fish.getId(),
                        fish.getName(),
                        fish.getQty(),
                        fish.getNormalPrice(),
                        fish.getWholesaleprice()


                );
                obList.add(tm);
            }

                List<SupFishDTO> supFishList = supFishBo.getAll();
                for (SupFishDTO supFish : supFishList) {

                   SupFishTm tm1 = new SupFishTm(
                            supFish.getFisId(),
                            supFish.getSupId(),
                            supFish.getDate(),
                            supFish.getQty(),
                           supFish.getAmount()
                    );
                   obList2.add(tm1);

            }


            tblAccSupFIsh.setItems(obList2);
            tblFish.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colFishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColFishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colNoramalPrice.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));
        colWholeSalePrice.setCellValueFactory(new PropertyValueFactory<>("wholesaleprice"));

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colFishId2.setCellValueFactory(new PropertyValueFactory<>("FishId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));


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

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtFishId.setText("");
        txtFishName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtFishId.getText();

        try {
            boolean isDeleted = fishBo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish deleted!").show();
                clearFields();
                loadAllFish();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=txtFishId.getText();
        String name = txtFishName.getText();
        String qtyOnHand = txtQtyOnHand.getText();
        double normalPrice = 0;
        double wholeSalePrice = 0;
        try {
            normalPrice = Double.parseDouble(txtNormalPrice.getText());
            wholeSalePrice = Double.parseDouble(txtWholeSalePrice.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!");
        }

        String supId=supplierBo.getId(cmbSup.getValue());
        System.out.println("supId = " + supId);
        int Qty= 0;
        double amount= 0;
        try {
            Qty = Integer.parseInt(txtQtyOnHand.getText());
            amount = Double.parseDouble(txtpurchasedAmount.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!");
        }
        Date date = Date.valueOf(LocalDate.now());



        try {
            if(id.isEmpty() || name.isEmpty() || qtyOnHand.isEmpty() || supId.isEmpty() ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

        SupFishDTO supFish = new SupFishDTO(id, supId, date, Qty,amount);
        FishDTO fish = new FishDTO(id, name, qtyOnHand,normalPrice,wholeSalePrice);


        try {
            boolean isSaved = fishBo.save(fish);
            if (isSaved) {
                boolean isSaved1 = supFishBo.save(supFish);
                if (isSaved1 ) {
                    clearFields();
                    loadAllFish();
                    getCurrentOrderId();
                    new Alert(Alert.AlertType.CONFIRMATION, "Fish saved!!!.").show();

                }
            }
        } catch (SQLException e) {
                new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();

            }

    }

    private void clearFields() {
        txtFishId.setText("");
        txtFishName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
        lblSup.setText("");
        txtpurchasedAmount.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String id=txtFishId.getText();
        String name = txtFishName.getText();
        String qtyONnHand = txtQtyOnHand.getText();
        double normalPrice = Double.parseDouble(txtNormalPrice.getText());
        double wholeSalePrice = Double.parseDouble(txtWholeSalePrice.getText());

        FishDTO fish = new FishDTO(id, name, qtyONnHand,normalPrice,wholeSalePrice);

        try {
            boolean isUpdated = fishBo.update(fish);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllFish();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtFishId.getText();

      FishDTO fish = fishBo.searchById(id);
        if (fish != null) {
            txtFishId.setText(fish.getId());
            txtFishName.setText(fish.getName());
            txtQtyOnHand.setText(fish.getQty());
            txtNormalPrice.setText(String.valueOf(fish.getNormalPrice()));
            txtWholeSalePrice.setText(String.valueOf(fish.getWholesaleprice()));


        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }
    }

    @FXML
    void cmbSupOnAction(ActionEvent event) {
        String id = cmbSup.getValue();
        try {
            Supplier supplier = supplierBo.searchById(id);

            lblSup.setText(supplier.getName());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addNewQtyFish.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("AddNewQty Form");

        stage.show();

    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtFishId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtFishName);
    }

    public void txtIQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.QTY,txtQtyOnHand);
    }

    public void txtNormalPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtNormalPrice);
    }

    public void txtIdWholeSalePriceKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtWholeSalePrice);
    }

    public void txtPurchaseAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,txtpurchasedAmount);
    }
}
