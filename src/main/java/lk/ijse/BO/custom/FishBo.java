package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FishBo extends SuperBo {
    public  boolean save(Fish fish) throws SQLException, ClassNotFoundException ;

    public  boolean update(Fish fish) throws SQLException, ClassNotFoundException ;

    public  Fish searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<Fish> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public boolean updateQty1(String fishId, int qty) throws SQLException, ClassNotFoundException ;

    public  boolean updateSupFish(int qty, String id) throws SQLException, ClassNotFoundException ;

    public Map<String, Integer> GetFishDetail() throws SQLException, ClassNotFoundException ;
}
