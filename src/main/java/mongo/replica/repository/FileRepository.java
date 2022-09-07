package mongo.replica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongo.replica.entities.FileEntitye;

public interface FileRepository extends MongoRepository<FileEntitye, String> {

	public FileRepository findByfilename(String filename);

}
