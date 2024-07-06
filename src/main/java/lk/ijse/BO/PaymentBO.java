package lk.ijse.BO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Entity.Payment;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO {
    public  boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException ;

    public  boolean update(PaymentDTO payment) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

    public  Payment searchById(String id) throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public  List<String> getType() ;
    public PaymentDTO searchByOrId(String id) throws SQLException, ClassNotFoundException ;

    public  List<String> getOrIds() throws SQLException, ClassNotFoundException ;

    public  boolean update1(String id, double advance1, double amountToPaid, String status) throws SQLException, ClassNotFoundException ;
}
