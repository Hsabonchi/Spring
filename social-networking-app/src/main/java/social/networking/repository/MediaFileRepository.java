package social.networking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import social.networking.entity.MediaFile;

@Repository
public interface MediaFileRepository extends MongoRepository<MediaFile, String> {
}
