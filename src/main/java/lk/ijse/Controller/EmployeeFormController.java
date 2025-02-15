package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.util.Duration;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.EmployeeBo;
import lk.ijse.BO.custom.impl.EmployeeBoImpl;
import lk.ijse.BO.custom.impl.FishBoImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.utill.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeFormController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableColumn<?, ?> ColEmpNIC;

    @FXML
    private TableColumn<?, ?> ColEmpName;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpContact;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpNIC;

    @FXML
    private TextField txtEmpName;

    EmployeeBo employeeBo = (EmployeeBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.EMPLOYEE);

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();

    }

    private void getCurrentOrderId() {
        try {
            String currentId = employeeBo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtEmpId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

            String[] split = currentId.split("[eE]+");

            int idNum = Integer.parseInt(split[1]);

            return "E" + String.format("%03d", ++idNum);

        }

        return "E001";
    }

    private void loadAllCustomers() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> employeeList = employeeBo.getAll();
            for (EmployeeDTO employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getContact(),
                        employee.getNIC(),
                        employee.getAddress()

                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColEmpNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

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
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmpId.getText();

        try {
            boolean isDeleted = employeeBo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String contact = txtEmpContact.getText();
        String NIC = txtEmpNIC.getText();
        String address = txtEmpAddress.getText();

        try {
            if(id.isEmpty() || name.isEmpty() || contact.isEmpty() || NIC.isEmpty() || address.isEmpty() ) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!").show();
                return;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }


        EmployeeDTO employee = new EmployeeDTO(id,name,contact,NIC,address);

        try {
            boolean isSaved = employeeBo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                setCellValueFactory();
                loadAllCustomers();
                clearFields();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String contact = txtEmpContact.getText();
        String NIC = txtEmpNIC.getText();
        String address = txtEmpAddress.getText();

        EmployeeDTO employee = new EmployeeDTO(id, name, contact, NIC, address);
        try {
            boolean isUpdate = employeeBo.update(employee);
            if(isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtEmpId.getText();

        EmployeeDTO employee = employeeBo.searchById(id);
        if (id != null) {
            txtEmpId.setText(employee.getId());
            txtEmpName.setText(employee.getName());
            txtEmpContact.setText(employee.getContact());
            txtEmpNIC.setText(employee.getNIC());
            txtEmpAddress.setText(employee.getAddress());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtEmpContact.setText("");
        txtEmpNIC.setText("");
        txtEmpAddress.setText("");


    }


    private void clearFields() {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtEmpContact.setText("");
        txtEmpNIC.setText("");
        txtEmpAddress.setText("");
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ID,txtEmpId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NAME,txtEmpName);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.CONTACT,txtEmpContact);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.NIC,txtEmpNIC);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.utill.TextField.ADDRESS,txtEmpAddress);
    }
}
