package com.minimatash.others;

import com.minimatash.entities.Department;

import java.util.List;
import java.util.Map;

public class DepartmentSearcher {
    public void search(List<Map<String, Object>> rows, List<Department> departments){
        for (Map row : rows) {
            Department department = new Department();
            department.setDepId((Integer) (row.get("depId")));
            department.setDepName((String) row.get("depName"));
            departments.add(department);
        }
    }
}
