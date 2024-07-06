package lk.ijse.BO.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.Order;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderBo extends SuperBo {
    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;

    public List<String> getStatus();
    public  List<String> getIds() throws SQLException, ClassNotFoundException ;
    public Map<String, Integer> getOrderCountByDay() throws SQLException, ClassNotFoundException ;
}
