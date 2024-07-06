package lk.ijse.BO;

import lk.ijse.dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface PlaceOrderBo {
    public  boolean orders(PlaceOrderDTO pl) throws SQLException;
}
