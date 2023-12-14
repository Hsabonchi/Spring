## Java-Hibernate-Maven- SpringBoot
Resouces_Name  |Link
--- |---
**Dr.Sun** |[Entity-Relationship Model](https://www.youtube.com/watch?v=gWm3lL7HZUg)
**Dr.Sun** |[Object-Relational Mapping with Hibernate and JPA (I)](https://www.youtube.com/watch?v=L91Lbinp_RU)
**Dr.Sun** |[Object Relational Mapping with Hibernate and JPA (II)](https://www.youtube.com/watch?v=KGM3LqDaO8s)
**Dr.Sun** |[Spring - Aspect Oriented Programming](https://www.youtube.com/watch?v=evjB-SXnZ78)
**Dr.Sun** |[Spring - Web MVC](https://www.youtube.com/watch?v=9_Dldx4fTpQ)
**Dr.Sun** |[Spring - Web Services](https://www.youtube.com/watch?v=srqJ5ujwsA4)
**Dr.Sun** |[REST API with Spring Boot](https://www.youtube.com/watch?v=EsLDbRbEcJU)
**Dr.Sun** |[Secure REST API](https://www.youtube.com/watch?v=V-iiEUsJpC0)
**Dr.Sun** |[Create a Spring Boot Project](https://csns.calstatela.edu/wiki/content/cysun/course_materials/cs5220/spring-boot-rest/)
** **      |[JPA OneToMany Relationship](https://youtu.be/qLAT48GP2xc)
** **      |[JPA ManyToMany Relationship ](https://youtu.be/fFeHwMKeHKc)
** **      |[JPA OneToOne Relationship](https://youtu.be/rY0MH-WUZVs)
** **       |[Java Rest Design](https://youtu.be/41PfGySNbx0)
---

* [JPA-Tutorial](https://github.com/RameshMF/JPA-Tutorial) |   [Java persistence with JPA](https://www.infoworld.com/article/3373652/java-persistence-with-jpa-and-hibernate-part-1-entities-and-relationships.html)
* [Interface EntityTransaction](https://www.objectdb.com/api/java/jpa/EntityTransaction).
* [Hibernate Query Language](https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm).
* [JPA Mapping Annotations](https://www.javaguides.net/2018/11/all-jpa-annotations-mapping-annotations.html)
---

* JPA  Operations that modify database content, such as a store, update, and delete should only be performed within an active transaction.
  	---
  - `entityManager.getTransaction().begin();` | `entityManager.getTransaction().commit();`| `entityManagerFactory.close();`
  ---
  A simple Persistent class should follow some rules
  ----
* A no-arg constructor: It is recommended that you have a default constructor at least package visibility so that hibernate can create the   instance of the Persistent class by newInstance() method.
* Provide an identifier property: It is better to assign an attribute as id. This attribute behaves as a primary key in a database.
* Declare getter and setter methods: The Hibernate recognizes the method by getter and setter method names by default.
* Prefer non-final class: Hibernate uses the concept of proxies, that depends on the persistent class. The application programmer will not be able to use proxies for lazy association fetching.


---
   The relationships between Entity classes are as follows:

- @ManyToOne Relation
- @OneToMany Relation
- @OneToOne Relation
- @ManyToMany Relation

- @OneToMany Relation:In a one-to-many relationship between Table A and Table B, each row in Table A is linked to 0, 1 or many rows in Table B.
   > In a One-to-Many/Many-to-One relationship, the owning side is usually defined on the ‘many' side of the relationship. It's usually the side which owns the     foreign key.The @JoinColumn annotation defines that actual physical mapping on the owning side.
   >  the value of mappedBy is the name of the association-mapping attribute on the owning side



- @ Many-To-One relation between entities: Where one entity (column or set of columns) is/are referenced with another entity (column or set of columns) which contain unique values. In relational databases these relations are applicable by using foreign key/primary key between tables.








Common Errors
---
 * hibernate_sequence  does not exist. 
    * [error performing isolated work](https://coderanch.com/t/487173/databases/hibernate-sequence-exist).
 * PK mappmed to FK [@JoinColumn](https://stackoverflow.com/questions/11938253/whats-the-difference-between-joincolumn-and-mappedby-when-using-a-jpa-onetoma)
 * Use of @OneToMany or @ManyToMany targeting an unmapped class `You forgot the @Entity annotation on the ***** class.`
