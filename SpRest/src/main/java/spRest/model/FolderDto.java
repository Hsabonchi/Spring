package spRest.model;

public class FolderDto {

    private Integer id;

    private String name;

    private Integer ParentId;

    private String ParentName;
    
    boolean isFolder;
    
    public FolderDto()
    {
    	
    
    }

    public FolderDto(Folder folder)
    {
    	 id = folder.getId();
         name = folder.getName();
         if (folder instanceof Folder) {
        	 isFolder=true;
        	  }
         
         if( folder.getParent() != null )
         {
         	ParentId = folder.getParent().getId();
         	ParentName = folder.getParent().getName();
         }
    }
    
    
   	public FolderDto(UFiles file){
   		id = file.getId();
           name = file.getName();
           if( file.getFolder() != null )
           {
           	ParentId = file.getFolder().getId();
           	ParentName = file.getFolder().getName();
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

	public Integer getParentId() {
		return ParentId;
	}

	public void setParentId(Integer parentId) {
		ParentId = parentId;
	}

	public String getParentName() {
		return ParentName;
	}

	public void setParentName(String parentName) {
		ParentName = parentName;
	}


	public boolean isFolder() {
		return isFolder;
	}


	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
  
}
