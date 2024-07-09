package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupFishDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupFishBo extends SuperBo {
    public  boolean save(SupFishDTO supFish) throws SQLException, ClassNotFoundException ;
    public List<SupFishDTO> getAll() throws SQLException, ClassNotFoundException;
}
