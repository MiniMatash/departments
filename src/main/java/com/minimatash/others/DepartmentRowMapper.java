package com.minimatash.others;

import com.minimatash.entities.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepId(rs.getInt("depId"));
        department.setDepName(rs.getString("depName"));
        return department;
    }
}
