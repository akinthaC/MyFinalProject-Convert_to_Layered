package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.PlaceOrderBo;
import lk.ijse.Db.DbConnection;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AccessoriesDao;
import lk.ijse.dao.custom.FishDao;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.dto.PlaceOrderDTO;
import lk.ijse.repository.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBo {
    AccessoriesDao accessoriesDao =(AccessoriesDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ACCESSORIES);
    FishDao fishDao =(FishDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FISH);
    Connection connection = null;
    @Override
    public boolean orders(PlaceOrderDTO pl) throws SQLException, ClassNotFoundException {
         connection = DbConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(pl.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = false;
                for (OrderDetailDTO od : pl.getOdlist()) {
                    if (od.getFishId() == null) {

                        isQtyUpdated = accessoriesDao.updateQty(od.getAccId(),od.getQty());

                    } else {
                        isQtyUpdated = fishDao.updateQty1(od.getFishId(),od.getQty());
                    }
                }

                if (isQtyUpdated) {
                    boolean isSave=false;
                    for (OrderDetailDTO od : pl.getOdlist()){
                        if (od.getFishId() == null) {

                            isSave = accessoriesOrderRepo.save(od.getOrdId(),od.getAccId(),od.getStatus(),od.getQty(),od.getDescription(),od.getDate());

                        } else {
                            isSave = fishOrderRepo.save(od.getOrdId(),od.getFishId(),od.getStatus(),od.getQty(),od.getDescription(),od.getDate());
                        }
                    }
                    if (isSave ) {
                        boolean isSave1 = false;
                        for (OrderDetailDTO od : pl.getOdlist()) {
                            isSave1 = orderEmployeeRepo.save(od.getOrdId(),od.getEmpId());
                        }
                        if (isSave1) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
