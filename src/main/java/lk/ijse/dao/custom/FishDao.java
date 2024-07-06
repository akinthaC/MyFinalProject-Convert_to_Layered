package lk.ijse.dao.custom;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.CrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public interface FishDao extends CrudDao<Fish> {
    public  Map<String, Integer> GetFishDetail() throws SQLException, ClassNotFoundException;
    public boolean updateQty1(String fishId, int qty) throws SQLException, ClassNotFoundException;

    public  boolean updateSupFish(int qty, String id) throws SQLException, ClassNotFoundException;
}
