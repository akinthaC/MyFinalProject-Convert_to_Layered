package lk.ijse.dao.custom;

import lk.ijse.Entity.AccessoriesOrder;
import lk.ijse.dao.CrudDao;

import java.sql.Date;
import java.sql.SQLException;

public interface AccessoriesOrderDao extends CrudDao<AccessoriesOrder> {
    public  boolean save1(String ordId, String accId,  int qty, String description,String status, Date date) throws SQLException, ClassNotFoundException;
}
