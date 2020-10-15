package spRest.model;

public class FileDto {
	
	
	private Integer id;

    private String name;

    private Integer FolderId;

    private String FoldertName;

    public FileDto(){
    	
    }
    
	public FileDto(UFiles file){
		id = file.getId();
        name = file.getName();
        if( file.getFolder() != null )
        {
        	FolderId = file.getFolder().getId();
        	FoldertName = file.getFolder().getName();
        }
	    	
     }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFolderId() {
		return FolderId;
	}

	public void setFolderId(Integer folderId) {
		FolderId = folderId;
	}

	public String getFoldertName() {
		return FoldertName;
	}

	public void setFoldertName(String foldertName) {
		FoldertName = foldertName;
	}
    
	  
	
	    
}