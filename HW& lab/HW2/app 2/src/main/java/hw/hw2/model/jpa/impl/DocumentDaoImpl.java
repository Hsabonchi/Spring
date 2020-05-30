package hw.hw2.model.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hw.hw2.model.Document;
import hw.hw2.model.Revision;
import hw.hw2.model.dao.DocumentDao;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Document saveDocument(Document document) {
		return entityManager.merge(document);
	}

	@Override
	public List<Document> allDocuments() {
		List<Document> alldocs = entityManager.createQuery("from Document", Document.class).getResultList();
		return alldocs;
	}

	@Override
	public Document getDocumentByid(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Document.class, id);
	}

	@Override
	@Transactional
	public Revision addRevision(Revision v) {
		// TODO Auto-generated method stub
		return entityManager.merge(v);
	}

}
