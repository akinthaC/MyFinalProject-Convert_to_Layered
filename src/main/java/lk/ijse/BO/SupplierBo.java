package lk.ijse.BO;

import lk.ijse.Entity.Supplier;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBo {
    public  String getId(String value) throws SQLException, ClassNotFoundException ;

    public List<String> searchNIC() throws SQLException, ClassNotFoundException ;

    public SupplierDTO searchByNIC(String nic) throws SQLException, ClassNotFoundException ;

    public  boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException ;

    public  boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

    public  Supplier searchById(String id) throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;
}
