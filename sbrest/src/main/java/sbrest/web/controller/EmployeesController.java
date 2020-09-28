package sbrest.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbrest.model.Employee;
import sbrest.model.EmployeeDto;
import sbrest.model.dao.EmployeeDao;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping
    public List<EmployeeDto> list()
    {
        List<Employee> employees = employeeDao.getEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for( Employee employee : employees )
            employeeDtos.add( new EmployeeDto( employee ) );
        return employeeDtos;
    }

    @GetMapping("/{id}")
    public EmployeeDto get( @PathVariable Integer id )
    {
        Employee employee = employeeDao.getEmployee( id );
        if( employee == null )
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                "Employee not found" );
        return new EmployeeDto( employee );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto add( @RequestBody EmployeeDto employeeDto )
    {
        if( !StringUtils.hasText( employeeDto.getName() ) )
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
                "Employee name is required" );

        Employee employee = new Employee();
        employee.setName( employeeDto.getName() );
        if( employeeDto.getSupervisorId() != null )
        {
            Employee supervisor = employeeDao
                .getEmployee( employeeDto.getSupervisorId() );
            employee.setSupervisor( supervisor );
        }

        employee = employeeDao.saveEmployee( employee );
        return new EmployeeDto( employee );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update1( @PathVariable Integer id,
        @RequestBody EmployeeDto employeeDto )
    {
        // Omit error check code
        // ...
        Employee employee = employeeDao.getEmployee( id );
        employee.setName( employeeDto.getName() );
        if( employeeDto.getSupervisorId() != null )
        {
            Employee supervisor = employeeDao
                .getEmployee( employeeDto.getSupervisorId() );
            employee.setSupervisor( supervisor );
        }
        else
            employee.setSupervisor( null );

        employee = employeeDao.saveEmployee( employee );
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update2( @PathVariable Integer id,
        @RequestBody Map<String, Object> patch )
    {
        // Omit error checking code
        // ...
        Employee employee = employeeDao.getEmployee( id );
        for( String key : patch.keySet() )
        {
            switch( key )
            {
                case "name":
                    employee.setName( (String) patch.get( key ) );
                    break;
                case "supervisorId":
                    if( patch.get( key ) != null )
                    {
                        Employee supervisor = employeeDao
                            .getEmployee( (Integer) patch.get( key ) );
                        employee.setSupervisor( supervisor );
                    }
                    else
                        employee.setSupervisor( null );
                    break;
                default:
            }
        }
        employee = employeeDao.saveEmployee( employee );
    }
}
