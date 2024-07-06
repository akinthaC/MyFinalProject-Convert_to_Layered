package lk.ijse.dao.custom.impl;

import lk.ijse.Db.DbConnection;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDao;
import lk.ijse.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    public  boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("INSERT INTO employee VALUES(?,?,?,?,?)",employee.getId(),employee.getName(),employee.getContact(),employee.getNIC(),employee.getAddress());

    }

    public  boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("UPDATE employee SET empName = ?, contact = ?, NIC = ?, address = ? WHERE empId = ?",employee.getName(),employee.getContact(),employee.getNIC(),employee.getAddress(),employee.getId());
    }

    public  EmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM employee WHERE empId = ?",id);
        if (resultSet.next()) {
            String empid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact=resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);

            EmployeeDTO employee = new EmployeeDTO(empid,name,contact,NIC,address);


            return employee;
        }

        return null;
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeSQL("DELETE FROM employee WHERE EmpId = ?",id);
    }

    public  List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeSQL("SELECT * FROM employee");

        List<EmployeeDTO> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String address = resultSet.getString(5);


            EmployeeDTO employee = new EmployeeDTO(id, name, contact, NIC, address);
            empList.add(employee);
        }
        return empList;


    }

    public  String getCurrentId() throws SQLException, ClassNotFoundException {


        ResultSet resultSet =SQLUtil.executeSQL("SELECT empId FROM employee ORDER BY empId DESC LIMIT 1");
        if(resultSet.next()) {
            String EMPID = resultSet.getString(1);
            return EMPID;
        }
        return null;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.executeSQL("SELECT empId FROM employee");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
