package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface PlaceOrderBo extends SuperBo {
    public  boolean orders(PlaceOrderDTO pl) throws SQLException, ClassNotFoundException;
}
