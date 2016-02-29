package com.minimatash.others;

import com.minimatash.entities.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getInt("departmentId"));
        department.setDepartmentName(rs.getString("departmentName"));
        return department;
    }
}
