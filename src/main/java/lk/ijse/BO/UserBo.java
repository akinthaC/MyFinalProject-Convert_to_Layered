package lk.ijse.BO;

import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBo {
    public List<String> searchName() throws SQLException, ClassNotFoundException ;
    public  boolean update(String Password, String userName) throws SQLException, ClassNotFoundException ;

    public  boolean update1(String email, String userName) throws SQLException, ClassNotFoundException ;
}
