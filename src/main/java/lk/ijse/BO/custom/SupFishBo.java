package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupFishDTO;

import java.sql.SQLException;

public interface SupFishBo extends SuperBo {
    public  boolean save(SupFishDTO supFish) throws SQLException, ClassNotFoundException ;
}
