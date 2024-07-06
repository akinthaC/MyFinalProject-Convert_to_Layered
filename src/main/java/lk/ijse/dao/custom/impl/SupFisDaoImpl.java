package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupFishDao;
import lk.ijse.dto.SupFishDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupFisDaoImpl implements SupFishDao {
    public  boolean save(SupFish supFish) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO fish_supplier VALUES(?,?,?,?,?) ",supFish.getFishId(),supFish.getSupId(),supFish.getDate(),supFish.getQty(),supFish.getAmount());

    }

    @Override
    public boolean update(SupFish DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  List<SupFish> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM fish_supplier ");

        List<SupFish> fishList = new ArrayList<>();

        while (resultSet.next()) {
            String fishid = resultSet.getString(1);
            String supid = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            int qty = (int) Double.parseDouble(resultSet.getString(4));
            double amount = resultSet.getDouble(5);



            SupFish supFish = new SupFish(fishid,supid,date,qty,amount);
            fishList.add(supFish);
        }
        return fishList;

    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupFish searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
