package lk.ijse.dao.custom;

import lk.ijse.Entity.FishOrder;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.CrudDao;

import java.sql.Date;
import java.sql.SQLException;

public interface FishOrderDao extends CrudDao<FishOrder> {
    public  boolean save1(String ordId, String fishId, int qty, String description, String status, Date date) throws SQLException, ClassNotFoundException ;
}
