package spRest.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import spRest.model.Folder;
import spRest.model.UFiles;

public interface FileDao {
	
	 
	List<UFiles> getListFiles();
	List<UFiles> getchilds(Folder  folder);
	List<UFiles> getTopLevelFiles();
	UFiles getFile(Integer id);
	UFiles getFilebyName(String filename);
	UFiles  getSpecFiles(String filename,Folder folder);
	UFiles save(UFiles f);
	void    deleteFiles(UFiles file);
	boolean checkFile(String filename, Folder folder);
	boolean checkTopLevFile(String filename);
	UFiles getSpecToplevFiles(String filename);
	

	

}
