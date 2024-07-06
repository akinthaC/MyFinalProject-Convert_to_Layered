package lk.ijse.BO;

import lk.ijse.dao.SQLUtil;

import java.sql.Date;
import java.sql.SQLException;

public interface FishOrderBo {
    public  boolean save(String ordId, String fishId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException ;

}
