package spRest.model.dao.jpa;

import java.nio.file.Files;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import spRest.model.Folder;
import spRest.model.dao.FolderDao;



@Repository
public class FolderDaoImpl implements FolderDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Folder> getFolders() {
		
		
		return entityManager.createQuery( "from Folder ", Folder.class )
            .getResultList();
	}

	 @Override
	 @Transactional
	public Folder save(Folder foldername) {
		   return entityManager.merge( foldername );
	}

	@Override
	public Folder getFolder(Integer id) {
		return entityManager.find( Folder.class, id );
	}

	@Override
	@Transactional
	public void deleteFolder(Folder folder) {
		 entityManager.remove( folder );
		
	}

	@Override
	public List<Folder> numrofChild(Folder folder) {
		   return  entityManager.createQuery( " from Folder e where Parent=:Parent", Folder.class )
		.setParameter("Parent",folder ).getResultList();				
	}

	@Override
	public List<Folder> topLevel() {
		return  (List<Folder>) entityManager.createQuery( "from Folder where Parent is null", Folder.class ).getResultList();
	}

	@Override
	public List<Folder> getChilds(Folder Parent) {
		return  (List<Folder>) entityManager.createQuery( "from Folder where Parent=:Parent", Folder.class ).setParameter("Parent", Parent).getResultList();
	}
}
