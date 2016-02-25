package com.minimatash.others;

import com.minimatash.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("empId"));
        employee.setDepartmentId(rs.getInt("depId"));
        employee.setFirstName(rs.getString("firstName"));
        employee.setLastName(rs.getString("lastName"));
        employee.setDateOfBirth(rs.getDate("dateOfBirth"));
        employee.setValidityOfContract(rs.getInt("validityOfContract"));
        return employee;
    }
}
