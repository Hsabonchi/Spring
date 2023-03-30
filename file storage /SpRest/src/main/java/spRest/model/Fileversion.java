package spRest.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="version")
public class Fileversion {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String verNum;
	
	private int size ;
	
	@CreationTimestamp
	private Date date_created;
	
	// one file has many verions
	@ManyToOne
	//(cascade = CascadeType.REMOVE)
	UFiles  file;

	// file type like txt or .jpg
    String type;
		
	
	//array of content -
	@Lob
	private byte[] content;
		
	// constructure
	public Fileversion(String verNum, int size, UFiles file, byte[] content,String type) {
		super();
		this.verNum = verNum;
		this.size = size;
		this.file = file;
		this.content = content;
		this.type=type;
	}

	public Fileversion()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersionNumber() {
		return verNum;
	}

	public void setVersionNumber(String versionNumber) {
		this.verNum = verNum;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public UFiles getFile() {
		return file;
	}

	public void setFile(UFiles file) {
		this.file = file;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	
 	
}
