package courseLink;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.dto.CourseInfoBySkill;
import com.project.dto.Courses;

public interface CourseRepository extends MongoRepository<Courses, String>{
	
	@Query(value="{ 'skill' : ?0}")
    List<CourseInfoBySkill> getCoursesBySkill(String skill);
}
