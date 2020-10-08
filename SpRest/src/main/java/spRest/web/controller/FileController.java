package spRest.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spRest.model.FileDto;
import spRest.model.Fileversion;
import spRest.model.Folder;
import spRest.model.FolderDto;
import spRest.model.StorageService;
import spRest.model.UFiles;
import spRest.model.dao.FileDao;
import spRest.model.dao.FolderDao;
import spRest.model.dao.VersionDao;



@RestController
@RequestMapping("/files")
public class FileController {
	
	  @Autowired
	    private FileDao  fileDao;
	  
	  @Autowired
	   private FolderDao folderDao;
	  
	  @Autowired
	  private VersionDao   versionDao;
	  
	  @GetMapping
	    public List<UFiles> list(ModelMap Models)
	   {
	    	
	  	  List<UFiles> Listfiles=fileDao.getListFiles();
	     return  Listfiles;
	    }
	 
	  //Upload a file at the top-level
	   @PostMapping("/upload")
	    @ResponseStatus(HttpStatus.CREATED)
	    public String upload( @RequestParam("file") MultipartFile file) throws IOException
	    {	   
		   String fileName = file.getOriginalFilename();
		    Folder folder=null;
		    
		   if (fileDao.checkTopLevFile(fileName)){
			  // Get the list of versions 
			  //get file obj by name and folder 
			  UFiles fileObj=fileDao.getSpecToplevFiles(fileName);
			  // UPdate  the current version
			  fileObj.setCurVersion(fileObj.getCurVersion()+1);
	
			  List<Fileversion> ListFv=versionDao.getfilesVers(fileObj);
			  String versionNum="v"+(ListFv.size()+1);
			  Fileversion fv=  new Fileversion(versionNum,
			     (int) file.getSize(),fileObj,
			     file.getBytes(),file.getContentType());
			       versionDao.save(fv);     
			   return "You successfully Uploaded File  "+fileName+ " File exist at toplevel";
		   }
		   
		   UFiles f= new UFiles();
		   f.setName(fileName);
		   f.setCurVersion(f.getCurVersion()+1);
		   UFiles mangedObj=fileDao.save(f);
		   // add files to file version
		   Fileversion fv=  new Fileversion("v1",
                   (int) file.getSize(),mangedObj,
                   file.getBytes(),
                   file.getContentType());
		           versionDao.save(fv);
		                  
		return  "You successfully Uploaded File  "+fileName+ " File not exist at toplevel" ;
		  
	    }
	
	   // Upload a file in a folder). 
	    @PostMapping("/{id}/upload")
	    @ResponseStatus(HttpStatus.CREATED)
	    public boolean uploadToFolder( @PathVariable Integer id,@RequestParam("file") MultipartFile file) throws IOException
	    {
		   // get folder by its ID
		    Folder folder =folderDao.getFolder(id);
		   // check if filename already exist
	       // get file from multipart
		    String fileName = file.getOriginalFilename();		   
		   //If a file with the same name already exists in the folder,
		  if (fileDao.checkFile(fileName,folder)) {
			   
			  //get file obj by name and folder 
			  UFiles fileObj=fileDao.getSpecFiles(fileName,folder);
			  // UPdate  the current version
			  fileObj.setCurVersion(fileObj.getCurVersion()+1);
			  // Get the list of versions 
		      List<Fileversion> ListFv=versionDao.getfilesVers(fileObj);
		      
			  String versionNum="v"+(ListFv.size()+1);
			  
			  Fileversion fv=  new Fileversion(versionNum,
		      (int) file.getSize(),fileObj,file.getBytes(),file.getContentType());
		       versionDao.save(fv);
			  return true;   	    		
		    }else {
		    	   UFiles f= new UFiles();
     			   f.setName(fileName);
				   f.setFolder(folder);
				   // UPdate  the current version
				   f.setCurVersion(f.getCurVersion()+1);
				   UFiles mangedObj=fileDao.save(f);
				   Fileversion fv=  new Fileversion("v1",
				                    (int) file.getSize(),mangedObj,
				                   file.getBytes(),
				                   file.getContentType());
						           versionDao.save(fv);
		    	                    
						           return false;
		    }  
		
	    }
	   
	   //Download a file- the latest version if no version number is given  
	    @GetMapping("/download/{id}")
	    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
	   
		  // check how many version number are there?		 
	      int currentVer=versionDao.getfilesVers(fileDao.getFile(id)).size();
	      
	      String version="v"+currentVer;
	      Fileversion  fversion =versionDao.getSpecVer(version,fileDao.getFile(id));

	       
		  
		  return ResponseEntity.ok()
		            .contentLength( fversion.getSize())
		            .contentType(MediaType.parseMediaType(fversion.getType()) )
		            .body(fversion.getContent());            
	    }

	    //
	    @GetMapping("/download/{id}/{number}")
	    public ResponseEntity<byte[]> downloadVersion(@PathVariable Integer id,@PathVariable Integer number) {
	    	  
	    	  String version="v"+number;
		      Fileversion  fversion =versionDao.getSpecVer(version,fileDao.getFile(id));
	    		    	    		    
	    	 return ResponseEntity.ok()
			            .contentLength( fversion.getSize())
			            .contentType(MediaType.parseMediaType(fversion.getType()) )
			            .body(fversion.getContent());      
	             
	    }       
	   
	 
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public FileDto add( @RequestBody FileDto fileDto )
	    {
	        if( !StringUtils.hasText( fileDto.getName() ) )
	            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
	                "Folder name is required" );
	        UFiles f1 = new UFiles();
	        f1.setName( fileDto.getName() );
	        //f1.setDate(new Date());
	        if( fileDto.getFolderId() != null )
	        {
	            Folder folder = folderDao.getFolder( fileDto.getFolderId());
	            f1.setFolder( folder );
	        }
	        f1 = fileDao.save(f1);
	        return new FileDto( f1 );
	       }
	    
	    // {filename:.+}
	    @DeleteMapping("/{id}")
	    String deleteFiles(@PathVariable Integer id) {
	    	// get id from URL
	    	//UFiles fileobj=fileDao.getFilebyName(filename);
	    	UFiles fileobj=fileDao.getFile(id);
	    	int totalDel=fileobj.getCurVersion()+1;
	    	fileDao.deleteFiles(fileobj);
	    	return "You have deleted  "+fileobj.getName()+ "Total number of deleted files and its version  "+  totalDel;
	    }
	    	 
	     


	

	     
     
	    
	   
		   
}
