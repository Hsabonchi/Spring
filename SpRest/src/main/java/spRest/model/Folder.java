package spRest.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table

public class Folder implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	
	
	@ManyToOne
	private Folder Parent ;
	
	@OneToMany(mappedBy="folder",cascade=CascadeType.REMOVE)
    private List<UFiles> file;
	
	@OneToMany(mappedBy="Parent", cascade=CascadeType.REMOVE)
	private List <Folder> childfoler;
	
	
	@CreationTimestamp
	private Date date_created;
	
	public Folder( String name) {
		super();
		this.name = name;
	
	}
	public Folder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Folder(String name, Folder parent) {
		super();
		this.name = name;
		Parent = parent;
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
	public  void setName(String name) {
		this.name = name;
	}
	public Folder getParent() {
		return Parent;
	}
	public void setParent(Folder parent) {
		Parent = parent;
	}
	public List<UFiles> getFile() {
		return file;
	}
	public void setFile(List<UFiles> file) {
		this.file = file;
	}
	
	

	
	
}
