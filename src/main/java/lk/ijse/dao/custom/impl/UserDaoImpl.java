package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.Entity.User;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public  List<String> searchName() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL( "SELECT userName  FROM user");
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            idList.add(name);
        }
        return idList;
    }

    @Override
    public boolean save(User DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User DTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public User searchByName(String name) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM user WHERE userName = ?",name);
        if (resultSet.next()) {
            String name1 = resultSet.getString(1);
            String password = resultSet.getString(2);
            String email = resultSet.getString(3);

            User user = new User(name1, password, email);


            return user;
        }

        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean update(String Password, String userName) throws SQLException, ClassNotFoundException {


        return SQLUtil.executeSQL("UPDATE user SET password = ? WHERE userName = ?",Password,userName);

    }

    public  boolean update1(String email, String userName) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE user SET email = ? WHERE userName = ?",email,userName);
    }
}
