package sbrest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbrest.model.Employee;
import sbrest.model.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getEmployees()
    {
        return entityManager.createQuery( "from Employee", Employee.class )
            .getResultList();
    }

    @Override
    public Employee getEmployee( Integer id )
    {
        return entityManager.find( Employee.class, id );
    }

    @Override
    @Transactional
    public Employee saveEmployee( Employee employee )
    {
        return entityManager.merge( employee );
    }
}
