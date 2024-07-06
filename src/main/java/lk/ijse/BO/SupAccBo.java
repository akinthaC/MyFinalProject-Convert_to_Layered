package lk.ijse.BO;

import lk.ijse.Entity.SupAcc;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupAccDao;
import lk.ijse.dto.SupAccDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupAccBo {
    public List<SupAccDTO> getAll() throws SQLException, ClassNotFoundException ;
    public  List<String> getIds() throws SQLException, ClassNotFoundException ;
    public  boolean save(SupAccDTO supAcc) throws SQLException, ClassNotFoundException ;

}
