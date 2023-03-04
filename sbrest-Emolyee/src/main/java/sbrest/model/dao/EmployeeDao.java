package sbrest.model.dao;

import java.util.List;

import sbrest.model.Employee;

public interface EmployeeDao {

    List<Employee> getEmployees();

    Employee getEmployee( Integer id );

    Employee saveEmployee( Employee employee );
}
