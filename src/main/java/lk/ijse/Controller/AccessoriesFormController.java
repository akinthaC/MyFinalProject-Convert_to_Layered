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
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.AccessoriesBo;
import lk.ijse.BO.custom.SupAccBo;
import lk.ijse.BO.custom.SupplierBo;
import lk.ijse.BO.custom.impl.AccessoriesBoImpl;
import lk.ijse.BO.custom.impl.CustomerBoImpl;
import lk.ijse.BO.custom.impl.SupAccBoImpl;
import lk.ijse.BO.custom.impl.SupplierBOImpl;
import lk.ijse.Entity.Supplier;
import lk.ijse.dao.custom.AccessoriesDao;
import lk.ijse.dao.custom.impl.AccessoriesDaoImpl;
import lk.ijse.dto.AccessoriesDTO;
import lk.ijse.dto.SupAccDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.model.tm.AccessoriesTm;
import lk.ijse.model.tm.SupAccTm;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccessoriesFormController {
    @FXML
    public TableView <SupAccTm> tblAccSup;
    @FXML
    public TableColumn <?, ?>colFishId2;
    @FXML
    public TableColumn <?, ?>colQty;
    @FXML
    public TextField purchasedAmount;
    @FXML
    public JFXButton btnAdd;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbSupplier;

    @FXML
    private TableColumn<?, ?> colAccessoriesId;

    @FXML
    private TableColumn<?, ?> colAccessoriesName;

    @FXML
    private TableColumn<?, ?> colNoramalPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colWholeSalePrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblSupName;

    @FXML
    private TableView<AccessoriesTm> tblAccessories;

    @FXML
    private TextField txtAccessorieId;

    @FXML
    private TextField txtAccessoriesName;

    @FXML
    private TextField txtNormalPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtWholeSalePrice;
    AccessoriesBo accessoriesBo = (AccessoriesBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.ACCESSORIES);
    SupAccBo supAccBo = (SupAccBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPACC);
    SupplierBo supplierBo = (SupplierBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.SUPPLIER);

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllAccessories();
        getCurrentAccId();
        getSupplierIds();

    }

    private void getSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = supplierBo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSupplier.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentAccId() {
        try {
            String currentId = accessoriesBo.getCurrentId();

            String nextAccId = generateNextAccId(currentId);
            txtAccessorieId.setText(nextAccId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextAccId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[aA]+");

            int idNum = Integer.parseInt(split[1]);

            return "A" + String.format("%03d", ++idNum);

        }

        return "A001";
    }

    private void loadAllAccessories() {
        ObservableList<AccessoriesTm> obList = FXCollections.observableArrayList();
        ObservableList<SupAccTm> obList2 = FXCollections.observableArrayList();

        try {
            List<AccessoriesDTO> accessoriesList = accessoriesBo.getAll();
            for (AccessoriesDTO accessories : accessoriesList) {
                 AccessoriesTm tm= new AccessoriesTm(
                        accessories.getId(),
                        accessories.getName(),
                        accessories.getQty(),
                        accessories.getNormalPrice(),
                        accessories.getWholesaleprice()
                );
                obList.add(tm);

            }
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


            tblAccSup.setItems(obList2);

            tblAccessories.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colAccessoriesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAccessoriesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colNoramalPrice.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));
        colWholeSalePrice.setCellValueFactory(new PropertyValueFactory<>("wholesaleprice"));

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colFishId2.setCellValueFactory(new PropertyValueFactory<>("AccId"));
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
        txtAccessorieId.setText("");
        cmbSupplier.getSelectionModel().clearSelection();
        txtAccessoriesName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtAccessorieId.getText();

        try {
            boolean isDeleted = accessoriesBo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Accessorie deleted!").show();
                clearFields();
                setCellValueFactory();
                loadAllAccessories();
                getCurrentAccId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtAccessorieId.setText("");
       // cmbSupplier.getSelectionModel().clearSelection();
        txtAccessoriesName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
        lblSupName.setText("");
        purchasedAmount.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=txtAccessorieId.getText();
        String name = txtAccessoriesName.getText();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();

        String supId=supplierBo.getId(cmbSupplier.getValue());
        System.out.println("supId = " + supId);
        int Qty= Integer.parseInt(txtQtyOnHand.getText());
        Date date = Date.valueOf(LocalDate.now());
        double amount= Double.parseDouble(purchasedAmount.getText());

        try {
            if(id.isEmpty() || name.isEmpty() || name.isEmpty() || qty.isEmpty() || normalPrice.isEmpty() || wholeSalePrice.isEmpty() || supId.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }



      AccessoriesDTO accessories = new AccessoriesDTO(id,name, qty,normalPrice,wholeSalePrice);
        SupAccDTO supAcc = new SupAccDTO(id, supId, date, Qty, amount);


        try {
            boolean isSaved = accessoriesBo.save(accessories);
            if (isSaved) {
                boolean isSaved1 = supAccBo.save(supAcc);
                if (isSaved1) {
                    clearFields();
                    setCellValueFactory();
                    loadAllAccessories();
                    getCurrentAccId();
                    new Alert(Alert.AlertType.CONFIRMATION, "Accessories saved!!!.").show();

                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        System.out.println("aaa");
        String id=txtAccessorieId.getText();
        String name = txtAccessoriesName.getText();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();

        try {
            if(id.isEmpty() || name.isEmpty() || name.isEmpty() || qty.isEmpty() || normalPrice.isEmpty() || wholeSalePrice.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        AccessoriesDTO accessories = new AccessoriesDTO(id, name, qty,normalPrice,wholeSalePrice);

        try {
            boolean isUpdated = accessoriesBo.update(accessories);
            System.out.println("aaalllll");System.out.println("update = " + isUpdated);
            if(isUpdated) {
                System.out.println("aaarrtgbfvgjk");
                new Alert(Alert.AlertType.CONFIRMATION, "Accessories updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllAccessories();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = cmbSupplier.getValue();
        try {
            Supplier supplier = supplierBo.searchById(id);

            lblSupName.setText(supplier.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAccessorieId.getText();

        AccessoriesDTO accessories = accessoriesBo.searchById(id);
        if (accessories != null) {
            txtAccessorieId.setText(accessories.getId());
            txtAccessoriesName.setText(accessories.getName());
            txtQtyOnHand.setText(accessories.getQty());
            txtNormalPrice.setText(accessories.getNormalPrice());
            txtWholeSalePrice.setText(accessories.getWholesaleprice());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Accessories not found!").show();
        }

    }


    @FXML
    void filterSupplierDe(KeyEvent event) {

    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtAccessorieId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtAccessoriesName);
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

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addNewQtyAccessories.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.setTitle("AddNewQty Form");

        stage.show();
    }

    public void txtPurchaseAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.AMOUNT,purchasedAmount);

    }
}
