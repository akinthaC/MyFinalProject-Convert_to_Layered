package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.OrderEmployee;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderEmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderEmployeeDaoImpl implements OrderEmployeeDao {
    public  boolean save(String ordId, String empId) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO order_employee VALUES(?,?)",ordId,empId);
    }

    @Override
    public boolean save(OrderEmployee DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderEmployee DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderEmployee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderEmployee searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
