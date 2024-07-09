package lk.ijse.BO.custom;


import lk.ijse.BO.SuperBo;
import lk.ijse.Entity.User;
import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBo extends SuperBo {
    public User searchByName(String name) throws SQLException, ClassNotFoundException;
    public List<String> searchName() throws SQLException, ClassNotFoundException ;
    public  boolean update(String Password, String userName) throws SQLException, ClassNotFoundException ;

    public  boolean update1(String email, String userName) throws SQLException, ClassNotFoundException ;

}
