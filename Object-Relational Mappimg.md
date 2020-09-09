<h1>Object-Relational Mappimg </h1>

| 1  | 2 |
| ------------- | ------------- |
|<img src="Images/m2.1-jpa.png" alt="alt text" width="400" height="270">|<img src="Images/m2.2-jpa.png" alt="alt text" width="400" height="270">

<h2>Data Model Calss</h2>
<h3>Persistent Class  </h3>
<ul>
A class whose objects need to be saved (i.e. persisted) in a database
<li> Any Java model class can be a persistent class, though it is recommendedthat </li>
  <li> Each persistent class has an identity field. </li>
  <li> Each persistent class implements the Serializableinterface</li><strong>Object of this class can be converted into binary and backs the main purpose of this requirement is to allow the object caching function in the orm tool to save the cache object on disks when memory cach is full </strong>
  <li> Each persistent field(filed need to be saved into DB) has a pair of getterand setter, which don’t have to be public</li>
</ul>

`@Entity
This annotation indicates that the class is mapped to a database table
 By default, the ORM framework understands that the class name is as same as the table name. 
The @Entity annotation must be placed before the class definition:`

`@Table(name = )
This annotation is used if the class name is different than the database table name, and it is must placed before the class definition.`

`@Id
    This annotation specifies that a field is mapped to a primary key column in the table.
    Declares the primary key of the entity`.
    
`@GeneratedValue
    If the values of the primary column are auto-increment, 
    we need to use this annotation to tell Hibernate knows, along with`
    
 [Interface EntityManager](https://docs.jboss.org/hibernate/jpa/2.2/api/javax/persistence/EntityManager.html):
  Interface used to interact with the persistence context.
An EntityManager instance is associated with a persistence context. A persistence context is a set of entity instances in which for any persistent entity identity there is a unique entity instance.he EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.

-- merge return a refrence to a manged object

<h3>JPQL</h3>
<ul>
  <li> It operates on classes object and  properties instead of tables columns </li>
  <li> The Select clause can be omitted </li>
  <li> we are selecting objects not columns </li>
  <li> Passing object as a vlaue of parameter</li>
  <li>  where properties - you can do supervisor.supervisor means property.property </li>
</ul>


  
