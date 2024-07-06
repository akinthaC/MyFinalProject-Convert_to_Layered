package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.dto.AccessoriesDTO;

import java.sql.SQLException;

public interface AccessoriesDao extends CrudDao<AccessoriesDTO> {
    public boolean updateQty(String accId, int qty) throws SQLException, ClassNotFoundException;

    public  boolean updateSupAcc(int qty, String accID) throws SQLException, ClassNotFoundException;
}
