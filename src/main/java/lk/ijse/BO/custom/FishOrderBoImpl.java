package lk.ijse.BO.custom;

import lk.ijse.BO.FishOrderBo;
import lk.ijse.Entity.FishOrder;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.impl.FishOrderDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class FishOrderBoImpl implements FishOrderBo {
    FishOrderDao fishOrderDao = new FishOrderDaoImpl();
    @Override
    public boolean save(String ordId, String fishId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException {
        return fishOrderDao.save(new FishOrder(ordId, fishId, qty, description, status, date));
    }
}
