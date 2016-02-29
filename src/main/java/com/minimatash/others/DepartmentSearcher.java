package com.minimatash.others;

import com.minimatash.entities.Department;

import java.util.List;
import java.util.Map;

public class DepartmentSearcher {
    public void search(List<Map<String, Object>> rows, List<Department> departments){
        for (Map row : rows) {
            Department department = new Department();
            department.setDepartmentId((Integer) (row.get("departmentId")));
            department.setDepartmentName((String) row.get("departmentName"));
            departments.add(department);
        }
    }
}
