package spRest.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spRest.model.Folder;
import spRest.model.FolderDto;
import spRest.model.UFiles;
import spRest.model.dao.FileDao;
import spRest.model.dao.FolderDao;
import spRest.model.dao.VersionDao;

@RestController
@RequestMapping("/folders")
public class FolderController {

@Autowired
private FolderDao folderDao;
@Autowired
private FileDao  fileDao;

@Autowired
private VersionDao   versionDao;

    
    //1) List all top-level files and folders
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<FolderDto> getTopLevel(ModelMap Models)
   { 
      //Top-Level Folder: get all folder where parent is null
  	  List<Folder> Topfolders=folderDao.topLevel();
  	  
  	  //Intiaite an arrayList of Dtos
  	   List<FolderDto> folderDtos= new ArrayList<FolderDto>();
  	   
  	 //Top-Level Files: get all folder where parent is null
  	  List<UFiles> TopFiles =fileDao.getTopLevelFiles() ;
  	  // add folders to Dto list 
  	  for (Folder f:Topfolders)
  		folderDtos.add(new FolderDto(f));
  	  
  	  // add files to Dto list 
  	  for (UFiles file:TopFiles)
   		folderDtos.add(new FolderDto(file)); 
  	   
     return  folderDtos;
    }
    
 //2)List all files and folders in an existing folder
@GetMapping("/{id}")
@ResponseStatus(HttpStatus.ACCEPTED)
public  List<FolderDto>  getChildren( @PathVariable Integer id )
{   
      //Intiaite an arrayList of Dtos
      List<FolderDto> folderDtos= new ArrayList<FolderDto>();
      
  	  //get Folders where id=Passing through url
      Folder  folderObj = folderDao.getFolder( id );
      List<Folder> foldersList=folderDao.getChilds(folderObj);
      
      for (Folder f:foldersList)
   		 folderDtos.add(new FolderDto(f));
      
       // get files where id=Passing through url
       List<UFiles>listChilds=fileDao.getchilds(folderObj);
      
      // add files to Dto list 
  	  for (UFiles file:listChilds)
   		folderDtos.add(new FolderDto(file));
   
    return folderDtos;
    
}
        
//3)Create a new top-level folder
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public FolderDto add( @RequestBody FolderDto dto )
{
    if( !StringUtils.hasText( dto.getName() ) )
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
            "Foldre name is required" );
    // Create new folder
    Folder folder = new Folder();
    // set the folder name
    folder.setName( dto.getName() );       
    if( dto.getParentId() != null )
    {
    	Folder parent = folderDao.getFolder( dto.getParentId());
    	folder.setParent(parent);
    }
    folder = folderDao.save( folder );
    return new FolderDto( folder );
}
    
       
//4)Create a new folder in an existing folder
@PostMapping("/{id}")
@ResponseStatus(HttpStatus.CREATED)
public FolderDto addToparent(@PathVariable Integer id,@RequestBody FolderDto dto )
{

    Folder Parentfolder = folderDao.getFolder(id);
    if (Parentfolder==null) {
		throw new  ResponseStatusException (HttpStatus.NOT_FOUND,"Folder not Found");
	}
	Parentfolder = folderDao.getFolder(id);
	
	
    // Create new folder
    Folder folder = new Folder();
    // set the folder name
    folder.setName( dto.getName() );
    
    Folder parent = folderDao.getFolder(Parentfolder.getId() );
	folder.setParent(parent);
	folder = folderDao.save( folder );
    return new FolderDto( folder );
}
    
    
@DeleteMapping("{id}")
String deleteFolder2222(@PathVariable Integer id){
	
	// get id from URL
	int  numofdel = 0;
	Folder Parentfolder=null;
	List<Folder> childFolder = null;
	
	if (folderDao.getFolder(id)==null) {
	   return "Folder is not exist";
	 }
	 //folders at first level
	Parentfolder = folderDao.getFolder(id);
    List<Folder> foldersList=folderDao.numrofChild(Parentfolder);
    
    if(foldersList.isEmpty()&& fileDao.getchilds(Parentfolder).isEmpty()) {
    		folderDao.deleteFolder(Parentfolder);
	    		return  "total numbers of the folders and files deleted  are is leave "+1;
            }
    else {

         /* Scan first level
    	  count No. of files and folders
    	  iterate over each folder in the list of subfolders
    	  if has subfolders send them to countchild function
    	*/
    	
    	  /* check if it has files - under the  parent folder
    	  counting n. of files */
    	
    	if (Parentfolder.getFile()!=null) {
			  numofdel+=Parentfolder.getFile().size();
			  
	          }
    	
    	   // count no. of children
    	  numofdel+=foldersList.size();
    	 // foldersList is a list of children
    	for (Folder f:foldersList) {
    		// check if the child has a child (sub foldder)---c1 hsa a c1.2
		    childFolder=folderDao.numrofChild(f);
		     
		    if(!childFolder.isEmpty()) {
			    counter=0;
			    int countChild=countChild(childFolder);
			    numofdel+=countChild;
			  }
		    // check if it has subfiles
		       numofdel+=f.getFile().size();
			}	  
    	numofdel+=1;
    	folderDao.deleteFolder(Parentfolder);
	    return  "total numbers of the folders and files deleted  are  "+numofdel;
    		 
     }
    
    }
        static int counter;
	    int countChild(List<Folder> childFolder) {
	    	
	      /* recived is a list of  childfolders
	       	 that has a child folder e.g c1 child of c (parent)
			 count No. of child - childFolder.size() if its not empty
			 iterate over each subfolder e.g C1 - 
			 count files under each subfolder
			 if numrofChild for the subfolder not empty
	         redo it the process again*/
	    	
	    	if(!childFolder.isEmpty() ) {
	    		counter+=childFolder.size();
	    		
    		for (Folder childfol:childFolder) {
    			
				if (childfol.getFile()!=null) {
					counter+=childfol.getFile().size();
					  
			        }
    			// check if it has sub folder
		    	List<Folder> subFolder =folderDao.numrofChild(childfol);
		    	if(!subFolder.isEmpty()) {
	    			countChild(subFolder);
	    		  }
		      }
		    	
	    	}
	    
	    	
			return counter;
	    	
	    	
	    }



    
//  
//  @GetMapping("/{id}")
//  public FolderDto get( @PathVariable Integer id )
//  {
//      Folder  folder = folderDao.getFolder( id );
//      
//      if( folder == null )
//          throw new ResponseStatusException( HttpStatus.NOT_FOUND,
//              "folder not found" );
//      return new FolderDto( folder );
//  }
  
  // Get files and Folders under a specific folder- by id 
  //List files and folders a folder    
    
    
    //
//  //Get All Folders
//  @GetMapping
//  public List<FolderDto> list(ModelMap Models)
// { 
//	  List<Folder> Topfolders=folderDao.getFolders();
//	  List<FolderDto> folderDtos= new ArrayList<FolderDto>();
//	
//	  for (Folder folder:Topfolders)
//		folderDtos.add(new FolderDto(folder));
//	 
//   return  folderDtos;
//  }
    
//  @DeleteMapping("/{id}")
//  String deleteFolder(@PathVariable Integer id)throws Exception {
//  	// get id from URL
//  	int  numofdel = 0;
//  	Folder Parentfolder;
//  	List<Folder> childFolder = null;
//		try {
//			if (folderDao.getFolder(id)==null) {
//				return "Folder is not exist";
//			}
//			Parentfolder = folderDao.getFolder(id);	
//			 // Files at first level
//			 numofdel+=Parentfolder.getFile().size();
//			 
//	    	 //folders at first level
//	    	 List<Folder> foldersList=folderDao.numrofChild(Parentfolder);	 
//	    	if(!foldersList.isEmpty()&& !fileDao.getchilds(Parentfolder).isEmpty()) {
//	    	
//	    		//folderDao.deleteFolder(Parentfolder);
//	    		return  "total numbers of the folders and files deleted  are  "+33;
//	       }
//	    		 else {
//	    			//folderDao.deleteFolder(Parentfolder);
//	 	    		return  "total numbers of the folders and files deleted  are  "+1;
//	    		 }
//		} catch (Exception e) {
//			throw new Exception("Generic Exception, id="+id);
//		}
//  	
//    }
   
    
    
    
}