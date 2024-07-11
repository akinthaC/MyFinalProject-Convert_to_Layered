package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.Order;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDao;
import lk.ijse.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    public  boolean save(Order order) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO orders VALUES(?,?,?,?)",order.getId(),order.getDate(),order.getHandOverDate(),order.getCusId());

    }

    @Override
    public boolean update(Order DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.executeSQL("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(resultSet.next()) {
            String ordId = resultSet.getString(1);
            return ordId;
        }
        return null;
    }

    @Override
    public Order searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    public  List<String> getStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("Successes");
        obList.add("Pending");

        return obList;
    }


    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT orderId FROM orders");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;

    }

    public  Map<String, Integer> getOrderCountByDay() throws SQLException, ClassNotFoundException {
        Map<String, Integer> OrderCOuntByDay = new HashMap<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT date, COUNT(*) AS order_count FROM orders GROUP BY date ORDER BY date ASC;");

        while (resultSet.next()) {

            String date = resultSet.getString("date");
            int orderCount = (int) resultSet.getDouble("order_count");
            OrderCOuntByDay.put(date, orderCount);

            return OrderCOuntByDay;
        }
        return OrderCOuntByDay;
    }
}
