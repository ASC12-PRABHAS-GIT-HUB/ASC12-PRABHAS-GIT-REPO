package com.VLS.course.service;

import com.VLS.course.entity.CourseEntity;

import java.util.List;

public interface CourseService {
    List<CourseEntity> getAllCourses();
    CourseEntity addCourse(CourseEntity course);
    void removeCourse(Long id);

    List<CourseEntity> searchByAuthor(String author);
    List<CourseEntity> searchByCourseName(String courseName);
}