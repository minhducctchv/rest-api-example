package mdt.edu.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mdt.edu.api.dao.StudentRepository;
import mdt.edu.api.entity.Student;

@RestController
public class HomeController {
	@Autowired
	StudentRepository studentRepository;

	// them
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student st) {
		Student newStudent = studentRepository.save(st);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newStudent.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	// xoa
	@DeleteMapping("/students/{id}")
	public void deleteOneStudent(@PathVariable int id) {
		studentRepository.deleteById(id);
	}
	
	// find all
	@GetMapping("/students")
	public List<Student> getAllStudent(){
		return studentRepository.findAll();
	}
	
	// find by id
	@GetMapping("/students/{id}")
	public Student getOneSutdent(@PathVariable int id) throws Exception {
		Optional<Student> optinal = studentRepository.findById(id);
		if(optinal.isPresent()) {
			return optinal.get();
		}else
			throw new Exception();
	}
}
