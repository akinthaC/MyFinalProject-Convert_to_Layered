package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.Fish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.FishDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FishBo extends SuperBo {
    public  boolean save(FishDTO fish) throws SQLException, ClassNotFoundException ;

    public  boolean update(FishDTO fish) throws SQLException, ClassNotFoundException ;

    public  FishDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<FishDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public boolean updateQty1(String fishId, int qty) throws SQLException, ClassNotFoundException ;

    public  boolean updateSupFish(int qty, String id) throws SQLException, ClassNotFoundException ;

    public Map<String, Integer> GetFishDetail() throws SQLException, ClassNotFoundException ;
}
