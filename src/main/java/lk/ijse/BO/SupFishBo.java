package lk.ijse.BO;

import lk.ijse.Entity.SupFish;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupFishDTO;

import java.sql.SQLException;

public interface SupFishBo {
    public  boolean save(SupFishDTO supFish) throws SQLException, ClassNotFoundException ;
}
