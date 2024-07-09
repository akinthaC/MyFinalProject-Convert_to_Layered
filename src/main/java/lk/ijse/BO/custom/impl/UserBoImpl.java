package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.UserBo;
import lk.ijse.Entity.User;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FishOrderDao;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dao.custom.impl.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);;

    @Override
    public User searchByName(String name) throws SQLException, ClassNotFoundException {
        return userDao.searchByName(name);
    }

    @Override
    public List<String> searchName() throws SQLException, ClassNotFoundException {
        return  userDao.searchName();
    }

    @Override
    public boolean update(String Password, String userName) throws SQLException, ClassNotFoundException {
        return userDao.update(Password, userName);
    }

    @Override
    public boolean update1(String email, String userName) throws SQLException, ClassNotFoundException {
        return userDao.update1(email, userName);
    }
}
