package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DashBoardDao;
import lk.ijse.dto.DashBoardDTO;

import java.sql.*;
import java.util.List;

public class DashBoardDaoImpl implements DashBoardDao {
    @Override
    public boolean save(DashBoardDTO DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(DashBoardDTO DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<DashBoardDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public DashBoardDTO searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getSaleCount(Date date) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.executeSQL("SELECT COUNT(*) FROM orders WHERE date = ?",date);
        if (rs.next()) {
            return String.valueOf(rs.getInt(1));
        } else {
            return "0";
        }
    }

    @Override
    public String getMostSaleFishWeekly() throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.executeSQL("SELECT fishId,description, SUM(qty) AS total_quantity_sold\n" +
                "FROM fish_order\n" +
                "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK) AND CURDATE()\n" +
                "GROUP BY fishId,description \n" +
                "ORDER BY total_quantity_sold DESC\n" +
                "LIMIT 1;");
        if (rs.next()) {
            return rs.getString("description");
        } else {
            //Platform.runLater(() -> lblMostSoldFish.setText("No sales data available for this week."));
        }
        return null;
    }

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {

        ResultSet rs =SQLUtil.executeSQL("SELECT COUNT(DISTINCT empId) AS employee_count FROM employee");
        if (rs.next()) {
            return rs.getString("employee_count");
        }
        return "0";
    }
}
