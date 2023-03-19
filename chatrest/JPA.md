Annotation

| Annotation       | Description |
| ----------- | ----------- |
| @Entity    | is a JPA annotation to make this object ready for storage in a JPA-based data store.       |
| @id      |  The Id annotation specifies the primary key property or field of an entitykey./  JPA annotations to indicate it’s the primary key and automatically populated by the JPA provider |
| @GeneratedValue | Together with an ID this annotation defines that this value is generated automatically|
|@SpringBootApplication|pulls in component scanning, autoconfiguration, and property support|
|@RestController|that the data returned by each method will be written straight into the response body instead of rendering a template|
|@autowired| Dependency Injection|
---

@SpringBootApplication is a convenience annotation that adds all of the following:

- @Configuration: Tags the class as a source of bean definitions for the  application context.
- @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,  other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
- @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers

## Notes
<ul>
<li> Entities are the persistence objects, stored as records in the database</li>
<li> Every JPA entity must have a primary key</li>
<li>The class must implement serializable.</li>
</ul>

---
### Join Annotations
 
| Annotation       | Description |
| ----------- | ----------- |
| @OneToOne   |             |
| @ManyToOne  |             |
| @OneToMany  |             |
| @ManyToMany |             |

I JPA relations are uni-directional; need to explicitly define any
bi-directional relationships
 “Many” is implemented via java.util.Set interface
 @JoinColumn - allows you to specify which column should be used to
join
 Many-to-many requires a @JoinTable specificati

 ---
## RESTful API 
<p> Common concept associated with REST is the Hypertext Transfer Protocol. In HTTP, a caller sends a Request to a URI. A web server receives the request, and routes it to a request handler. The handler creates a Response, which is then sent back to the caller.</p>


<p> @GetMapping, @PostMapping, @PutMapping and @DeleteMapping, corresponding to HTTP GET, POST, PUT, and DELETE calls.</p>

### RESTful CRUD operations:

- For CREATE: use HTTP method POST.
- For READ: use HTTP method GET.
- For UPDATE: use HTTP method PUT.
- For DELETE: use HTTP method DELETE.

`PUT replaces an entire record. Fields not supplied are replaced with null. You can use PATCH to update a subset of items.`


 ---
 ## To Standard Output
- The simplest way to dump the queries to standard out is to add the following to application.properties:
     - `spring.jpa.show-sql=true`
- To beautify or pretty print the SQL, we can add:
    - `spring.jpa.properties.hibernate.format_sql=true`

---
## Refrences
* [DAO vs Repository Patterns](https://www.baeldung.com/java-dao-vs-repository)
    - DAO and Repository pattern are ways of implementing Data Access Layer.
    - DAO is an abstraction of data persistence.
    - Repository is an abstraction of a collection of objects.
    - DAO would be considered closer to the database, often table-centric.
    - Repository would be considered closer to the Domain, dealing only in Aggregate Roots.
   - Repository could be implemented using DAO's, but you wouldn't do the opposite.

* [Persistence Context](https://www.youtube.com/watch?v=EkVsf46ze8o)  |  -    [What is Persistence Context?](https://stackoverflow.com/questions/19930152/what-is-persistence-context).

* [JPQL snippet](https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html) 