package com.example.demo.Repository;

import com.example.demo.Classes.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course, Long> {
}
