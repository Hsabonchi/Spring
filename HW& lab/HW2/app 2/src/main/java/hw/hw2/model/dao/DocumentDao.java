package hw.hw2.model.dao;

import java.util.List;

import hw.hw2.model.Document;
import hw.hw2.model.Revision;

public interface DocumentDao {
	Document saveDocument(Document document);

	List<Document> allDocuments();

	Document getDocumentByid(Integer id);

	Revision addRevision(Revision v);
}
