
> @Entity annotation is required to specify a JPA entity. The name attribute is optional, defaults to the entity class name without the  package. It is used to refer to the entity in JPQL (Java Persistence Query Language) queries

> @Table annotation is optional and used to refer to the table in a database, defaults to the entity name

> @Id annotation is required and used to specify the primary key of an entity (table in database). You will get an exception "No identifier specified for entity" if ignores it

> @GeneratedValue annotation provides the generation strategies for the values of primary keys

> @Column annotation is optional and used to map with the table column in the database, defaults to property or field name

> @OneToMany is used to specify the One-To-Many relationship association with another entity which is annotated with @ManyToOne

>  mappedBy is required in a bidirectional relationship to specify the field or property name of the owner entity of the relationship
  cascade is optional and used to specify which entity operations should be cascaded (propagated) to the associated entity, defaults to no  operations. cascade=ALL is equivalent to cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH}
 > fetch is optional and used to specify the strategy for the persistence provider runtime (Hibernate) to fetch data from the database
  > > There are two fetching strategies EAGER and LAZY, defaults to LAZY in ToMany associations (@OneToMany, @ManyToMany). When fetching data      for an entity, JPA and Hibernate will also fetch data for EAGER associations while the LAZY will be fetched on-demand.
> @JoinColumn annotation indicates that this entity will act as the owner of the relationship (This table has a column with a foreign key to the referenced table).
> `@ElementCollection` is mainly for mapping non-entities.
> It means that the collection is not a collection of entities, but a collection of simple types (Strings, etc.) or a collection of  embeddable elements .

> @ManyToOne is used to specify a single-value relationship association to another entity which annotated with @OneToMany

> fetch is optional and defaults to EAGER in ToOne associations (@ManyToOne, @OneToOne)
The EAGER strategy should be avoided in practice, as it generates and executes unnecessary SQL scripts thus adds more weight load to the underlying database

> @JoinColumn is used to specify the foreign key column in the underlying database table. In single join column, it is optional and the default attribute value will be used

> name is defaulted to the property or field name joins with an underscore character and the primary key name of the reference entity
referencedColumnName is defaulted to the primary key of the preferenced table.

`In a One-to-Many/Many-to-One relationship, the owning side is usually defined on the ‘many' side of the relationship. It's usually the side which owns the foreign key.The @JoinColumn annotation defines that actual physical mapping on the owning side`.

`When you traverse from the “Many” side to the “One” side, you only need to make reference to one object, which is why the Employee class holds a single reference to an Employer class via the private Employer employer instance variable`.

`However, when you traverse from the “One” to the “Many” side, you will need to hold a reference to MANY objects.`
