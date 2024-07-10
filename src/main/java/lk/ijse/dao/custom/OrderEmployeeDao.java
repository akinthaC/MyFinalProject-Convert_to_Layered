package lk.ijse.dao.custom;

import lk.ijse.Entity.OrderEmployee;
import lk.ijse.dao.CrudDao;

import java.sql.SQLException;

public interface OrderEmployeeDao extends CrudDao<OrderEmployee> {
    public  boolean save1(String ordId, String empId) throws SQLException, ClassNotFoundException;
}
