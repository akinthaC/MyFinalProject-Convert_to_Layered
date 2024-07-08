package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dao.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DashboardBo extends SuperBo {
    public String getSaleCount(Date date) throws SQLException, ClassNotFoundException ;


    public String getMostSaleFishWeekly() throws SQLException, ClassNotFoundException ;


    public String getEmployeeCount() throws SQLException, ClassNotFoundException ;
}
