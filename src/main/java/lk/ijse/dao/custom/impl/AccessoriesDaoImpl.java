package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Accessories;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.AccessoriesDao;
import lk.ijse.dto.AccessoriesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessoriesDaoImpl implements AccessoriesDao {
    public  boolean save(Accessories accessories) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO accessories VALUES(?,?,?,?,?)",accessories.getId(),accessories.getName(),accessories.getQty(),accessories.getNormalPrice(),accessories.getWholesaleprice());

    }

    public  boolean update(Accessories accessories) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE accessories SET name= ?, qtyOnHand = ?, normalPrice = ?, wholeSalePrice = ? WHERE accId = ?",accessories.getName(),accessories.getQty(),accessories.getNormalPrice(),accessories.getWholesaleprice(),accessories.getId());
    }



    public  boolean delete(String id) throws SQLException, ClassNotFoundException {


        return SQLUtil.executeSQL("DELETE FROM accessories WHERE accId = ?", id);
    }

    public  List<Accessories> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM accessories");

        List<Accessories> accessoriesList = new ArrayList<>();

        while (resultSet.next()) {
            String accid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String qty = resultSet.getString(3);
            String normalprice = resultSet.getString(4);
            String wholesaleprice = resultSet.getString(5);


            Accessories accessories= new Accessories(accid,name,qty,normalprice,wholesaleprice);
            accessoriesList.add(accessories);
        }
        return accessoriesList;


    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQLUtil.executeSQL("SELECT accId FROM accessories ORDER BY accId DESC LIMIT 1");

        if(resultSet.next()) {
            String accId = resultSet.getString(1);
            return accId;
        }
        return null;
    }



    public  Accessories searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM accessories WHERE accId = ?",id);

        if (resultSet.next()) {
            String accid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String qty=resultSet.getString(3);
            String normalprice = resultSet.getString(4);
            String wholesaleprice = resultSet.getString(5);
            ;
            Accessories accessories = new Accessories(accid,name,qty,normalprice,wholesaleprice);


            return accessories;
        }

        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT accId  FROM accessories");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }



    public boolean updateQty(String accId, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeSQL("UPDATE accessories SET qtyOnHand = qtyOnHand - ? WHERE accId = ?",qty,accId);
    }



    public  boolean updateSupAcc(int qty, String accID) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE accessories SET qtyOnHand = qtyOnHand + ? WHERE accId = ?",accID,qty);
    }
}
