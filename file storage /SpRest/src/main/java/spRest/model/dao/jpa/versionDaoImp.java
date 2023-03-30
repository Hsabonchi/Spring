package spRest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spRest.model.Fileversion;
import spRest.model.Folder;
import spRest.model.UFiles;
import spRest.model.dao.VersionDao;
@Repository
public class versionDaoImp implements VersionDao{

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Fileversion> getfilesVers(UFiles file) {
		return entityManager.createQuery( " from Fileversion e where file=:file",
				Fileversion.class )
				.setParameter("file",file ).getResultList();
		
	}
	
	@Override
	public  Fileversion getversion(String versionNumber) {
		return entityManager.createQuery("from Fileversion e where verNum=:verNum",
				Fileversion.class )
				.setParameter("verNum",versionNumber ).getSingleResult();	
		 	
	}
	@Transactional
	@Override
	public Fileversion save(Fileversion file) {
		   return  entityManager.merge( file );
	}
	 
	 
	 @Override
		public Fileversion getVersioId(Integer id) {
			return entityManager.find( Fileversion.class, id );
		}

	@Override
	public Fileversion getSpecVer(String filVersion, UFiles file) {
		
		 return entityManager.createQuery( " from Fileversion e where file=:file and verNum=:verNum",
				Fileversion.class )
				.setParameter("verNum",filVersion )
				.setParameter("file",file )
				.getSingleResult();
	}

}
