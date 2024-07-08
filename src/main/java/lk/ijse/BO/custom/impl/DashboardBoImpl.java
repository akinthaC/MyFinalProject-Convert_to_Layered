package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.DashboardBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.DashBoardDao;
import lk.ijse.dao.custom.impl.DashBoardDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class DashboardBoImpl implements DashboardBo {
    DashBoardDao dashBoardDao = (DashBoardDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.DASHBOARD);
    @Override
    public String getSaleCount(Date date) throws SQLException, ClassNotFoundException {
        return dashBoardDao.getSaleCount(date);
    }

    @Override
    public String getMostSaleFishWeekly() throws SQLException, ClassNotFoundException {
        return dashBoardDao.getMostSaleFishWeekly();
    }

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return dashBoardDao.getEmployeeCount();
    }
}
