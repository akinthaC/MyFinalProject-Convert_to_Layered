package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.FishDao;
import lk.ijse.dto.FishDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishDaoImpl implements FishDao {
    public  boolean save(Fish fish) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO fish VALUES(?,?,?,?,?) ",fish.getId(),fish.getName(),fish.getQty(),fish.getNormalPrice(),fish.getWholesaleprice());

    }

    public  boolean update(Fish fish) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE fish SET  name= ?, qtyOnHand = ?, normalPrice = ?, wholeSalePrice = ? WHERE FishId = ?",fish.getId(),fish.getQty(),fish.getNormalPrice(),fish.getWholesaleprice(),fish.getId());
    }

    public  Fish searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeSQL("SELECT * FROM fish WHERE fishId = ?",id);

        if (resultSet.next()) {
            String fishId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String qty=resultSet.getString(3);
            double normalPrice = Double.parseDouble(resultSet.getString(4));
            double WholeSalePrice = Double.parseDouble(resultSet.getString(5));


            Fish fish = new Fish(fishId, name, qty, normalPrice, WholeSalePrice);


            return fish;
        }

        return null;

    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("DELETE FROM fish WHERE fishId = ?",id);
    }

    public  List<Fish> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM fish");

        List<Fish> fishList = new ArrayList<>();

        while (resultSet.next()) {
            String fishid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String qty = resultSet.getString(3);
            double normalprice = Double.parseDouble(resultSet.getString(4));
            double wholesaleprice = Double.parseDouble(resultSet.getString(5));


            Fish fish = new Fish(fishid,name,qty,normalprice,wholesaleprice);
            fishList.add(fish);
        }
        return fishList;


    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT fishId FROM fish ORDER BY fishId DESC LIMIT 1");
        if(resultSet.next()) {
            String fishId = resultSet.getString(1);
            return fishId;
        }
        return null;
    }


    public  List<String> getIds() throws SQLException, ClassNotFoundException {


        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT fishId FROM fish");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }



    public boolean updateQty1(String fishId, int qty) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE fish SET qtyOnHand = qtyOnHand - ? WHERE fishId = ?",fishId,qty);
    }

    public  boolean updateSupFish(int qty, String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE fish SET qtyOnHand = qtyOnHand + ? WHERE fishId = ?",qty,id);


    }

    public  Map<String, Integer> GetFishDetail() throws SQLException, ClassNotFoundException {
        Map<String, Integer> FishDetail = new HashMap<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT name, qtyOnHand FROM fish;");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int qtyOnHand = (int) resultSet.getDouble("qtyOnHand");
                FishDetail.put(name, qtyOnHand);
            }

        return FishDetail;
    }
}
