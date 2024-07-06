package lk.ijse.BO;

import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupFishDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderEmployeeBo {
    public  boolean save(String ordId, String empId) throws SQLException, ClassNotFoundException ;
    public List<SupFishDTO> getAll() throws SQLException, ClassNotFoundException ;
}
