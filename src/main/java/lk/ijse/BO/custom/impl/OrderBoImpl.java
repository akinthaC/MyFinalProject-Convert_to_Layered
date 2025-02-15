package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.OrderBo;
import lk.ijse.Entity.Order;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.OrderDao;
import lk.ijse.dao.custom.impl.OrderDaoImpl;
import lk.ijse.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao=(OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);;
    @Override
    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDao.save(new Order(order.getId(),order.getDate(),order.getHandOverDate(),order.getCusId()));
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDao.getCurrentId();
    }

    @Override
    public List<String> getStatus() {
        return orderDao.getStatus();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
      return orderDao.getIds();
    }

    @Override
    public Map<String, Integer> getOrderCountByDay() throws SQLException, ClassNotFoundException {
        return orderDao.getOrderCountByDay();
    }
}
