package lk.ijse.BO.custom;

<<<<<<<< HEAD:src/main/java/lk/ijse/BO/custom/UserBo.java
import lk.ijse.BO.SuperBo;
import lk.ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBo extends SuperBo {
    public List<String> searchName() throws SQLException, ClassNotFoundException ;
    public  boolean update(String Password, String userName) throws SQLException, ClassNotFoundException ;

    public  boolean update1(String email, String userName) throws SQLException, ClassNotFoundException ;
========
public interface UserBo {
>>>>>>>> origin/main:src/main/java/lk/ijse/BO/UserBo.java
}
