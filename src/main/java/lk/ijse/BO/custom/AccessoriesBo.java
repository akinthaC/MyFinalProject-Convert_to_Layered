package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dao.CrudDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.AccessoriesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AccessoriesBo extends SuperBo {
    public  boolean save(AccessoriesDTO accessories) throws SQLException, ClassNotFoundException ;

    public  boolean update(AccessoriesDTO accessories) throws SQLException, ClassNotFoundException ;



    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public List<AccessoriesDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;



    public  AccessoriesDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;



    public boolean updateQty(String accId, int qty) throws SQLException, ClassNotFoundException ;



    public  boolean updateSupAcc(int qty, String accID) throws SQLException, ClassNotFoundException ;
}
