package FileManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FolderMain {
	// you can use EntityManager method find to retrieve 
	// the corresponding entity from the database without having to create a query.
    EntityManagerFactory entityManagerFactory = Persistence
        .createEntityManagerFactory( "hibernate-HW2" );
    
    EntityManager entityManager = entityManagerFactory
        .createEntityManager();

    

}
