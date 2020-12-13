package storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file);
	void delete(Path url, String file);
	Path getDirectorySave();
	Stream<Path> loadAll();

	Path load(String filename);
	void setRootLocation(Path rootLocation);
	Resource loadAsResource(String filename);

	void deleteAll();

	boolean searchFile(Path directorySave, String name);

}
