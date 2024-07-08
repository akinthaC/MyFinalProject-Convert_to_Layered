package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.FishOrderBo;
import lk.ijse.Entity.FishOrder;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.impl.FishOrderDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class FishOrderBoImpl implements FishOrderBo {
    FishOrderDao fishOrderDao = (FishOrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FISH);;
    @Override
    public boolean save(String ordId, String fishId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException {
        return fishOrderDao.save(new FishOrder(ordId, fishId, qty, description, status, date));
    }
}
