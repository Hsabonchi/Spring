package spRest.model.dao.jpa;

import java.nio.file.Files;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spRest.model.Folder;
import spRest.model.UFiles;
import spRest.model.dao.FileDao;

@Repository
public class FileDaoImp implements FileDao {
	@PersistenceContext
	private EntityManager entityManager;
	


	@Override
	public UFiles getFile(Integer id) {
		return entityManager.find( UFiles.class, id );
	}

	@Transactional
	@Override
	public UFiles save(UFiles f) {
		return entityManager.merge(f) ;
	}

	@Override
	public List<UFiles> getListFiles() {
		return entityManager.createQuery( "from UFiles", UFiles.class )
	            .getResultList();
	}

	@Override
	public UFiles getFilebyName(String name) {
		
		return 
			entityManager.createQuery( "from UFiles e where e.name=:name", UFiles.class)
			.setParameter("name",name )
			.getSingleResult();
	}

	@Override
	public List<UFiles> getTopLevelFiles() {
		return   
		entityManager.createQuery( "from UFiles where folder is null", UFiles.class )
		.getResultList();
	}

	@Override
	public List<UFiles> getchilds(Folder folder) {
		return 		
		entityManager.createQuery( "from UFiles e where e.folder=:folder", UFiles.class )
		.setParameter("folder", folder)
		.getResultList();
	}
	
	

	@Override
	public boolean checkFile(String filename,Folder folder) {
		
        Query qry =entityManager.
    	createQuery("from UFiles e where name=:name and folder=:folder", 
				 UFiles.class)
		.setParameter("name",filename )
        .setParameter("folder",folder );
        if(qry.getResultList().size() >0){
			return true;
		}
        else
        	return false;
	
       
	}

	@Override
	public boolean checkTopLevFile(String filename) {
		
        Query qry =entityManager.
    	createQuery("from UFiles e where name=:name and folder=null", 
				 UFiles.class)
		.setParameter("name",filename );
        if(qry.getResultList().size() >0){
			return true;
		}
        else
        	return false;
	
       
	}
	
	
	@Override
	@Transactional
	public void deleteFiles(UFiles file) {
		entityManager.remove( file );
		
	}

	

	@Override
	public UFiles getSpecFiles(String filename, Folder folder) {
		return 
		(UFiles) entityManager.createQuery( "from UFiles e where name=:name and folder=:folder",
				UFiles.class)
		.setParameter("name",filename )
		.setParameter("folder",folder )
		.getSingleResult();
	}

	@Override
	public UFiles getSpecToplevFiles(String filename) {
		return 
		(UFiles) entityManager.createQuery( "from UFiles e where name=:name and folder=null",
				UFiles.class)
		.setParameter("name",filename )
		.getSingleResult();
	}
	

}
