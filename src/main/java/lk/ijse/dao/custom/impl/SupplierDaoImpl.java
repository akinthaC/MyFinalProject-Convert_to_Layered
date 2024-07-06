package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Supplier;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDao;
import lk.ijse.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    public  String getId(String value) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL( "SELECT supId FROM supplier WHERE supId = ?",value);
        if (resultSet.next()) {
            String supID = resultSet.getString(1);
            return supID;
        }
        return null;
    }

    public  List<String> searchNIC() throws SQLException, ClassNotFoundException {


        List<String> idList = new ArrayList<>();

        ResultSet resultSet =SQLUtil.executeSQL("SELECT NIC   FROM supplier");
        while (resultSet.next()) {
            String Nic = resultSet.getString(1);
            idList.add(Nic);
        }
        return idList;
    }

    public Supplier searchByNIC(String nic) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL( "SELECT * FROM supplier WHERE NIC = ?",nic);
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String SupName = resultSet.getString(2);
            String Contact = resultSet.getString(3);
            String Nic = resultSet.getString(3);
            String address = resultSet.getString(3);

            Supplier supplier =new Supplier(supId, SupName,Contact,Nic,address);


            return supplier;
        }

        return null;
    }

    public  boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeSQL("INSERT INTO supplier VALUES(?,?,?,?,?)",supplier.getId(),supplier.getName(),supplier.getContact(),supplier.getNIC(),supplier.getAddress());

    }

    public  boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE supplier SET supName = ? , contact = ?, NIC = ?, address = ? WHERE supId = ? ",supplier.getName(),supplier.getContact(),supplier.getNIC(),supplier.getAddress(),supplier.getId());
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("DELETE FROM supplier WHERE supId = ?",id);
    }

    public  List<Supplier> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeSQL("SELECT * FROM supplier");

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);


            Supplier supplier = new Supplier(id, name, contact, NIC, address);
            supList.add(supplier);
        }
        return supList;
    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1");
        if(resultSet.next()) {
            String supID = resultSet.getString(1);
            return supID;
        }
        return null;
    }

    public  Supplier searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM supplier WHERE supId = ?",id);
        if (resultSet.next()) {
            String supid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact=resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);

            Supplier supplier= new Supplier(supid,name,contact,NIC,address);


            return supplier;
        }

        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT supId FROM supplier");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

}
