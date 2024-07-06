package lk.ijse.BO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBo {
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException ;

    public  boolean updateCustomer(CustomerDTO customer) throws SQLException ;

    public  CustomerDTO CusSearchById(String id) throws SQLException ;

    public  boolean deleteCustomer(String id) throws SQLException ;

    public  List<CustomerDTO> getAllCustomers() throws SQLException ;


    public  String getCurrentId() throws SQLException;

    public  List<String> getIds() throws SQLException ;
    public  List<String> getStatus() ;
}
