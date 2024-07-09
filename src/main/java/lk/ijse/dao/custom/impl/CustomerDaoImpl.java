package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Customer;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO customer VALUES(?,?,?,?,?,?)", customer.getId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getNIC(),customer.getType());

    }

    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE customer SET cusName = ? , contact = ?, NIC = ?, addrerss = ?, type = ? WHERE cusId = ? ", customer.getName(),customer.getContact(),customer.getNIC(),customer.getAddress(),customer.getType(),customer.getId());
    }

    public Customer searchById(String id) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM customer WHERE cusId = ?",id);

        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(5);
            String NIC = resultSet.getString(3);
            String address = resultSet.getString(4);
            String type = resultSet.getString(6);

            Customer customer = new Customer(cusId,name,contact,NIC,address,type);
            return customer;

        }
        return null;
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("DELETE FROM customer WHERE cusId = ?", id);
    }

    public  List<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM customer");

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);
            String type = resultSet.getString(6);

            Customer customer = new Customer(id, name,contact,NIC,address,type);
            cusList.add(customer);
        }
        return cusList;
    }


    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1");

        if(resultSet.next()) {
            String CUSID = resultSet.getString(1);
            return CUSID;
        }
        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT cusId FROM customer");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public  List<String> getStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("wholesale");
        obList.add("normal");

        return obList;
    }
}
