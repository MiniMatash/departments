package com.minimatash.others;

import com.minimatash.entities.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeSearcher {

    public void search(List<Map<String, Object>> rows, List<Employee> employees){
        for (Map row : rows) {
            Employee employee = new Employee();
            employee.setEmployeeId((Integer)(row.get("empId")));
            employee.setFirstName((String) row.get("firstName"));
            employee.setLastName((String) row.get("lastName"));
            employee.setDepartmentId((Integer) row.get("depId"));
            employee.setDateOfBirth((java.util.Date) row.get("dateOfBirth"));
            employee.setValidityOfContract((Integer) row.get("validityOfContract"));
            employees.add(employee);
        }
    }
}
