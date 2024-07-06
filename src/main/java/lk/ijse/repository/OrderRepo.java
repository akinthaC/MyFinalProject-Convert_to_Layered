package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.dto.OrderDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepo {
    public static boolean save(OrderDTO order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, order.getId());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getHandOverDate());
        pstm.setObject(4, order.getCusId());


        return pstm.executeUpdate() > 0;

    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String ordId = resultSet.getString(1);
            return ordId;
        }
        return null;
    }


    public static List<String> getStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("Successes");
        obList.add("Pending");

        return obList;
    }


    public static List<String> getIds() throws SQLException {
        String sql = "SELECT orderId FROM orders";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;

    }

    public static Map<String, Integer> getOrderCountByDay() {
        Map<String, Integer> OrderCOuntByDay = new HashMap<>();

        String sql = "SELECT date, COUNT(*) AS order_count FROM orders GROUP BY date ORDER BY date ASC;";

        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                int orderCount = (int) resultSet.getDouble("order_count");
                OrderCOuntByDay.put(date, orderCount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderCOuntByDay;
    }
}
