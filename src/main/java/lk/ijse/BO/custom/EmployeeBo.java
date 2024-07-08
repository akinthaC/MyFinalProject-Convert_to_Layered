package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    public  boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public  boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public  EmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  String getCurrentId() throws SQLException, ClassNotFoundException ;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;
}
