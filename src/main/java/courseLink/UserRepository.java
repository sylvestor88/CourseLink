package courseLink;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.dto.UserInfo;

public interface UserRepository extends MongoRepository<UserInfo, String> {
	
  
	
}
