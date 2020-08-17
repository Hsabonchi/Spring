
> @Entity annotation is required to specify a JPA entity. The name attribute is optional, defaults to the entity class name without the  package. It is used to refer to the entity in JPQL (Java Persistence Query Language) queries

> @Table annotation is optional and used to refer to the table in a database, defaults to the entity name

> @Id annotation is required and used to specify the primary key of an entity (table in database). You will get an exception "No identifier specified for entity" if ignores it

> @GeneratedValue annotation provides the generation strategies for the values of primary keys

> @Column annotation is optional and used to map with the table column in the database, defaults to property or field name

> @OneToMany is used to specify the One-To-Many relationship association with another entity which is annotated with @ManyToOne

>  mappedBy is required in a bidirectional relationship to specify the field or property name of the owner entity of the relationship
  cascade is optional and used to specify which entity operations should be cascaded (propagated) to the associated entity, defaults to no  operations. cascade=ALL is equivalent to cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH}
 > fetch is optional and used to specify the strategy for the persistence provider runtime (Hibernate) to fetch data from the database
  There are two fetching strategies EAGER and LAZY, defaults to LAZY in ToMany associations (@OneToMany, @ManyToMany). When fetching data for an entity, JPA and Hibernate will also fetch data for EAGER associations while the LAZY will be fetched on-demand.
> @JoinColumn annotation indicates that this entity will act as the owner of the relationship (This table has a column with a foreign key to the referenced table)
> `@ElementCollection` is mainly for mapping non-entities.
> It means that the collection is not a collection of entities, but a collection of simple types (Strings, etc.) or a collection of  embeddable elements 
