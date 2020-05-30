package hw.hw2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "documents_table")
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private int id;
	@Column
	@Lob
	private byte[] document;
	@JsonManagedReference
	@OneToMany(mappedBy = "mainDocument", cascade = CascadeType.ALL)
	private List<Revision> revisions;

	public Document() {
		revisions = new ArrayList<>();
	}

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

}
