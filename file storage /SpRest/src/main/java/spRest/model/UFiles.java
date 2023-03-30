package spRest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.MediaType;

@Entity
@Table

public class UFiles implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	// Many files belong to one folder
	@ManyToOne
	Folder folder;
	
	int  curVersion;
	
	// one file has many versions
    @OneToMany(mappedBy="file",cascade = CascadeType.REMOVE)
    private List <Fileversion> ListofVersion;

	
		
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurVersion() {
		return curVersion;
	}
	public void setCurVersion(int curVersion) {
		this.curVersion = curVersion;
	}
	
	
	
	 

}

