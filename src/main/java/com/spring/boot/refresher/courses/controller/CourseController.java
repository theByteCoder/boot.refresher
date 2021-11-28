package com.spring.boot.refresher.courses.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.refresher.courses.bean.Course;

@RestController
public class CourseController {

	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return Arrays.asList(new Course((long) 1, "JSP and Servelets", "theByteCoder"),
				new Course((long) 2, "Hibernate", "theByteCoder"),
				new Course((long) 3, "Spring framework", "theByteCoder"),
				new Course((long) 4, "Spring MVC", "theByteCoder"), new Course((long) 5, "Spring JPA", "theByteCoder"));
	}

	@RequestMapping("/courses/{id}")
	public Course getCourse(@PathVariable("id") String id) {
		Course result = null;
		switch (id) {
		case "1":
			result = new Course((long) 1, "JSP and Servelets", "theByteCoder");
			break;
		case "2":
			result = new Course((long) 2, "Hibernate", "theByteCoder");
			break;
		case "3":
			result = new Course((long) 3, "Spring framework", "theByteCoder");
			break;
		case "4":
			result = new Course((long) 4, "Spring MVC", "theByteCoder");
			break;
		case "5":
			result = new Course((long) 5, "Spring data JPA", "theByteCoder");
			break;
		default:
			result = null;
			break;
		}
		return result;
	}

}
