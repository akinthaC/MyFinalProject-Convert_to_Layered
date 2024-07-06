package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface AccessoriesOrderBo extends SuperBo {
    public  boolean save(String ordId, String accId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException;
}
