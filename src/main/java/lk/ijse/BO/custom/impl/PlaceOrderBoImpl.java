package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.PlaceOrderBo;
import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.*;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.dto.PlaceOrderDTO;


import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBo {
    AccessoriesDao accessoriesDao =(AccessoriesDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ACCESSORIES);
    FishDao fishDao =(FishDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FISH);
    OrderDao orderDao =(OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    AccessoriesOrderDao accessoriesOrderDao=(AccessoriesOrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ACCESSORIESORDER);
    FishOrderDao fishOrderDao = (FishOrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FISHORDER);
    OrderEmployeeDao orderEmployeeDao=(OrderEmployeeDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.OREDREMPLOYEE);
    Connection connection = null;
    @Override
    public boolean orders(PlaceOrderDTO pl) throws SQLException, ClassNotFoundException {
         connection = DbConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDao.save(new Order(pl.getOrder().getId(),pl.getOrder().getDate(), pl.getOrder().getHandOverDate(), pl.getOrder().getCusId()));
            System.out.println("isOrederSave"+isOrderSaved);
            if (isOrderSaved) {
                System.out.println("isOrederSave"+isOrderSaved);
                boolean isQtyUpdated = false;
                for (OrderDetailDTO od : pl.getOdlist()) {
                    if (od.getFishId() == null) {

                        isQtyUpdated = accessoriesDao.updateQty(od.getAccId(),od.getQty());

                    } else {
                        System.out.println("isOrederSave"+isOrderSaved);
                        isQtyUpdated = fishDao.updateQty1(od.getFishId(),od.getQty());
                    }
                }
                System.out.println("isQtyUpdated"+isQtyUpdated);
                if (isQtyUpdated) {
                    boolean isSave=false;
                    for (OrderDetailDTO od : pl.getOdlist()){
                        if (od.getFishId() == null) {

                            isSave = accessoriesOrderDao.save(new AccessoriesOrder(od.getOrdId(),od.getAccId(),od.getQty(),od.getDescription(),od.getStatus(),od.getDate()));

                        } else {
                            isSave = fishOrderDao.save1(od.getOrdId(),od.getFishId(),od.getQty(),od.getDescription(),od.getStatus(),od.getDate());
                        }
                    }
                    System.out.println("isSave"+isSave);
                    if (isSave ) {
                        boolean isSave1 = false;
                        for (OrderDetailDTO od : pl.getOdlist()) {
                            isSave1 = orderEmployeeDao.save1(od.getOrdId(),od.getEmpId());
                        }
                        System.out.println("isSave1"+isSave1);
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
