package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);    // ?? метод
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }
    @PutMapping
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO foundStudent = studentService.editStudent(studentDTO);
        if (foundStudent == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudentId(@PathVariable Long id) {
        studentService.deleteStudentId(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping
//    public ResponseEntity<Collection<Student>> findAllByAge(@RequestParam int age) {
//        return ResponseEntity.ok(studentService.findAllByAge(age));
//    }
//    @GetMapping
//    public ResponseEntity<Collection<Student>> findByAgeBetween(
//            @RequestParam(required = false) int min, int max) {
//        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
//    }
    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudents(
                @RequestParam(required = false) Integer age,
                @RequestParam(required = false) Integer min,
                @RequestParam(required = false) Integer max) {
            if (age != 0) {
                return ResponseEntity.ok(studentService.findAllByAge(age));
            }
            if (min != 0 && max != 0 && max > min) {
                return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
            }
        return ResponseEntity.notFound().build();
        }
    @GetMapping("{id}/faculty")
    public ResponseEntity<FacultyDTO> findFacultyByStudentId(Long id) {
       return ResponseEntity.ok(studentService.findFacultyByStudentId(id));
    }
}



