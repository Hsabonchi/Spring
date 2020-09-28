package sbrest.model;

public class EmployeeDto {

    private Integer id;

    private String name;

    private Integer supervisorId;

    private String supervisorName;

    public EmployeeDto()
    {
    }

    public EmployeeDto( Employee employee )
    {
        id = employee.getId();
        name = employee.getName();
        if( employee.getSupervisor() != null )
        {
            supervisorId = employee.getSupervisor().getId();
            supervisorName = employee.getSupervisor().getName();
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getSupervisorId()
    {
        return supervisorId;
    }

    public void setSupervisorId( Integer supervisorId )
    {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName()
    {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName )
    {
        this.supervisorName = supervisorName;
    }
}
