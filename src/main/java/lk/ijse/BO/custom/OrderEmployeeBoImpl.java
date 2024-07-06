package lk.ijse.BO.custom;

import lk.ijse.BO.OrderEmployeeBo;
import lk.ijse.Entity.OrderEmployee;
import lk.ijse.dao.custom.OrderEmployeeDao;
import lk.ijse.dao.custom.impl.OrderEmployeeDaoImpl;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.SupFishDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderEmployeeBoImpl implements OrderEmployeeBo {
    OrderEmployeeDao orderEmployeeDao=new OrderEmployeeDaoImpl();
    @Override
    public boolean save(String ordId, String empId) throws SQLException, ClassNotFoundException {
        return orderEmployeeDao.save(new OrderEmployee(ordId,empId));
    }

    @Override
    public List<SupFishDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
