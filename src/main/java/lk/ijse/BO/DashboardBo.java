package lk.ijse.BO;

import lk.ijse.dao.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DashboardBo {
    public String getSaleCount(Date date) throws SQLException, ClassNotFoundException ;


    public String getMostSaleFishWeekly() throws SQLException, ClassNotFoundException ;


    public String getEmployeeCount() throws SQLException, ClassNotFoundException ;
}
