package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.FishOrder;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.FishOrderDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class FishOrderDaoImpl implements FishOrderDao {
    public  boolean save(String ordId, String fishId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO fish_order VALUES(?,?,?,?,?,?)",ordId,fishId,qty,description,status,date);

    }


    @Override
    public boolean save(FishOrder DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(FishOrder DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<FishOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public FishOrder searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
