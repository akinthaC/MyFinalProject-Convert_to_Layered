package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.SupAcc;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupAccDao;
import lk.ijse.dto.SupAccDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupAccDaoImpl implements SupAccDao {
    public  List<SupAcc> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM accessories_supplier ");

        List<SupAcc> accList = new ArrayList<>();

        while (resultSet.next()) {
            String accId = resultSet.getString(1);
            String supid = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            int qty = (int) Double.parseDouble(resultSet.getString(4));
            double amount = resultSet.getDouble(5);


            SupAcc supAcc = new SupAcc(accId, supid, date, qty, amount);
            accList.add(supAcc);
        }
        return accList;

    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupAcc searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT accId FROM accessories");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }


    public  boolean save(SupAcc supAcc) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO accessories_supplier VALUES(?,?,?,?,?) ",supAcc.getAccId(),supAcc.getSupId(),supAcc.getDate(),supAcc.getQty(),supAcc.getAmount());
    }

    @Override
    public boolean update(SupAcc DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
