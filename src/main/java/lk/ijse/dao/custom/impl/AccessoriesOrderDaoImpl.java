package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.AccessoriesOrder;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.AccessoriesOrderDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccessoriesOrderDaoImpl implements AccessoriesOrderDao {
    public  boolean save(String ordId, String accId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO accessories_order VALUES(?,?,?,?,?,?)",ordId,accId,qty,description,status,date);
    }

    @Override
    public boolean save(AccessoriesOrder DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AccessoriesOrder DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<AccessoriesOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public AccessoriesOrder searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
