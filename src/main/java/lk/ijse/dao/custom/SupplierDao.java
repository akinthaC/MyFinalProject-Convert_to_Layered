package lk.ijse.dao.custom;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Supplier;
import lk.ijse.dao.CrudDao;
import lk.ijse.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDao extends CrudDao<Supplier> {

    public  String getId(String value) throws SQLException, ClassNotFoundException;

    public  List<String> searchNIC() throws SQLException, ClassNotFoundException;

    public  Supplier searchByNIC(String nic) throws SQLException, ClassNotFoundException;
}
