package lk.ijse.dao.custom;

import lk.ijse.Db.DbConnection;
import lk.ijse.dao.CrudDao;
import lk.ijse.dto.DashBoardDTO;

import java.sql.*;

public interface DashBoardDao extends CrudDao<DashBoardDTO> {
    public  String getSaleCount(Date date) throws SQLException, ClassNotFoundException;

    public  String getMostSaleFishWeekly() throws SQLException, ClassNotFoundException;

    public  String getEmployeeCount() throws SQLException, ClassNotFoundException;
}
