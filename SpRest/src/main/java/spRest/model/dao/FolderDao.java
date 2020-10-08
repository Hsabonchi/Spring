package spRest.model.dao;

import java.util.List;

import spRest.model.Folder;

public interface FolderDao {
	
	List<Folder> getFolders();
	Folder  save (Folder filename);
	Folder getFolder(Integer id);
	void    deleteFolder(Folder folder);
	List<Folder>     numrofChild(Folder folder);
	List<Folder>   topLevel();
	// get all child folders
	List<Folder> getChilds(Folder Parent);

}
