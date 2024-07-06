package lk.ijse.BO.custom;

import lk.ijse.BO.EmployeeBo;
import lk.ijse.Entity.Employee;
import lk.ijse.dao.custom.EmployeeDao;
import lk.ijse.dao.custom.impl.EmployeeDaoImpl;
import lk.ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDao.save(new Employee(employee.getId(),employee.getName(),employee.getContact(),employee.getNIC(),employee.getAddress())) ;
    }

    @Override
    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new Employee(employee.getId(),employee.getName(),employee.getContact(),employee.getNIC(),employee.getAddress())) ;
    }

    @Override
    public EmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDao.searchById(id);
        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getContact(),employee.getNIC(),employee.getAddress());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDao.delete(id);
    }

    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDao.getAll();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDTO.add(new EmployeeDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getContact(),
                    employee.getNIC(),
                    employee.getAddress()
            ));
        }
        return employeesDTO;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return employeeDao.getCurrentId();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return employeeDao.getIds();
    }
}
