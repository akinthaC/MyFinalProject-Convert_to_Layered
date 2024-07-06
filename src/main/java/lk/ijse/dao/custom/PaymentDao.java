package lk.ijse.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Payment;
import lk.ijse.dao.CrudDao;
import lk.ijse.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDao extends CrudDao<Payment> {
    public  List<String> getType() ;

    public  PaymentDTO searchByOrId(String id) throws SQLException, ClassNotFoundException;

    public  List<String> getOrIds() throws SQLException, ClassNotFoundException;

    public  boolean update1(String id, double advance1, double amountToPaid, String status) throws SQLException, ClassNotFoundException;
}
