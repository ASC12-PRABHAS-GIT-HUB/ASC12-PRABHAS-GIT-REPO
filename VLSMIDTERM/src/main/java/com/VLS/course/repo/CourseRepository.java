package com.VLS.course.repo;



import com.VLS.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    List<CourseEntity> findByCourseNameContainingIgnoreCase(String CourseName);
    List<CourseEntity> findByAuthorNameContainingIgnoreCase(String AuthorName);


}

