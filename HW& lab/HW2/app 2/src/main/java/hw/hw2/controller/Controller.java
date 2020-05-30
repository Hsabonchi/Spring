package hw.hw2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hw.hw2.model.Document;
import hw.hw2.model.Revision;
import hw.hw2.model.dao.DocumentDao;

@RequestMapping("Postman")

@RestController
public class Controller {

	@Autowired
	DocumentDao documentDao;

	@GetMapping
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/")

	@ResponseStatus(HttpStatus.CREATED)
	public List<Document> alldocuments() {
		return documentDao.allDocuments();
	}

	@PostMapping("/add")
	public String addDocument(@RequestParam("document") MultipartFile docu, @ModelAttribute Revision revision) {
		Document d = new Document();
		Revision firstRevision = new Revision();

		try {
			d.setDocument(docu.getBytes());
			firstRevision.setNotes(revision.getNotes());
			firstRevision.setVersionDocument(docu.getBytes());
			d.getRevisions().add(firstRevision);
			firstRevision.setMainDocument(d);

		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		documentDao.saveDocument(d);
		return "Success";

	}

	@PostMapping("/add/{id}/revision/")
	public String addVersion(@RequestParam("document") MultipartFile revisionFile, @ModelAttribute Revision revision,
			@PathVariable Integer id) throws IOException {
		Document d = documentDao.getDocumentByid(id);
		Revision r = new Revision();
		r.setNotes(revision.getNotes());
		r.setVersionDocument(revisionFile.getBytes());
		r.setMainDocument(d);

		documentDao.addRevision(r);

		return "Added";
	}
}
