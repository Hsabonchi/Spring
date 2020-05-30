package hw.hw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "revisions_table")
public class Revision implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	private Integer id;

	@Column
	@Lob
	private byte[] revisionDocument;

	@Column
	private String notes;

	@ManyToOne
	@JsonBackReference
	private Document mainDocument;

	public Integer getId() {
		return id;
	}

	public Document getMainDocument() {
		return mainDocument;
	}

	public void setMainDocument(Document mainDocument) {
		this.mainDocument = mainDocument;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public byte[] getVersionDocument() {
		return revisionDocument;
	}

	public void setVersionDocument(byte[] versionDocument) {
		this.revisionDocument = versionDocument;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
