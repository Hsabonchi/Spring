package spRest.model;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
//The above interface declares several abstract methods for initializing,
//storing, removing and retrieving files. It only lists possible storage operations 
//without their implementation. Now, it is up to you to decide how you want to
//implement them. In this example, we will use our file system for handling files. 
//It can also be implemented to store the files on any external location

public interface StorageService {

    void init();

    static String store(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

    Stream<Path> loadAll();

    Path load(String filename);

    static Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

    void deleteAll();

}