package lk.ijse.BO.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BO.SuperBo;
import lk.ijse.Db.DbConnection;
import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public  boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public  CustomerDTO CusSearchById(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public  List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;


    public  String getCurrentId() throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  List<String> getStatus() ;

}
