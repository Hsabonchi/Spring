##Java ## Hibernate # Maven. # SpringBoot

---
* [JPA-Tutorial](https://github.com/RameshMF/JPA-Tutorial)
* [Interface EntityTransaction](https://www.objectdb.com/api/java/jpa/EntityTransaction).
* [Hibernate Query Language](https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm).
* [JPA Mapping Annotations](https://www.javaguides.net/2018/11/all-jpa-annotations-mapping-annotations.html)
---
*`@ElementCollection` is mainly for mapping non-entities.
   * It means that the collection is not a collection of entities, but a collection of simple types (Strings, etc.) or a collection of  embeddable elements 
* `@OneToMany` is used to map entities
* .getSingleResult()
   * Execute a SELECT query that returns a single result.
   *  Specified by: getSingleResult() in Query
   *   Returns:
   *   the result
   *  Throws:
   * NoResultException - if there is no result.
* JPA  Operations that modify database content, such as a store, update, and delete should only be performed within an active transaction.
  	---
  - `entityManager.getTransaction().begin();`
  - `entityManager.getTransaction().commit();`
  - `entityManagerFactory.close();`
  ---
  A simple Persistent class should follow some rules
  ----
* A no-arg constructor: It is recommended that you have a default constructor at least package visibility so that hibernate can create the   instance of the Persistent class by newInstance() method.
* Provide an identifier property: It is better to assign an attribute as id. This attribute behaves as a primary key in a database.
* Declare getter and setter methods: The Hibernate recognizes the method by getter and setter method names by default.
* Prefer non-final class: Hibernate uses the concept of proxies, that depends on the persistent class. The application programmer will not be able to use proxies for lazy association fetching.


---
Common Errors
---
 * hibernate_sequence  does not exist. 
    * [error performing isolated work](https://coderanch.com/t/487173/databases/hibernate-sequence-exist).
 * PK mappmed to FK [@JoinColumn](https://stackoverflow.com/questions/11938253/whats-the-difference-between-joincolumn-and-mappedby-when-using-a-jpa-onetoma)
