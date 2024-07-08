package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.AccessoriesOrderBo;
import lk.ijse.Entity.AccessoriesOrder;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AccessoriesOrderDao;
import lk.ijse.dao.custom.impl.AccessoriesOrderDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class AccessoriesOrderBoImpl implements AccessoriesOrderBo {
    AccessoriesOrderDao accessoriesOrderDao=(AccessoriesOrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ACCESSORIESORDER);
    @Override
    public boolean save(String ordId, String accId, String status, int qty, String description, Date date) throws SQLException, ClassNotFoundException {
        return accessoriesOrderDao.save(new AccessoriesOrder(ordId, accId, qty, description,status,date));
    }
}
