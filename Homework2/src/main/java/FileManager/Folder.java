package FileManager;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Folders")

public class Folder implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", unique=true)
	private String name;
	@ManyToOne
	private Folder Parent ;
	
	
	
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
	
//	public int getParent_id() {
//		return parent_id;
//	}
//	public void setParent_id(int parent_id) {
//		this.parent_id = parent_id;
//	}
	
	
	
}
