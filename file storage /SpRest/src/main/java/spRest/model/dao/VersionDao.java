package spRest.model.dao;

import java.util.List;

import spRest.model.Fileversion;
import spRest.model.Folder;
import spRest.model.UFiles;


public interface VersionDao {
	
	List<Fileversion> getfilesVers(UFiles file);
	Fileversion save(Fileversion file);
	Fileversion getVersioId(Integer id);
	Fileversion getversion(String filVersion);
	Fileversion getSpecVer(String filVersion,UFiles file);

}
