package lk.ijse.dao.custom;

import lk.ijse.Entity.Order;
import lk.ijse.dao.CrudDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderDao extends CrudDao<Order> {
    public  List<String> getStatus();
    public  Map<String, Integer> getOrderCountByDay() throws SQLException, ClassNotFoundException;
}
