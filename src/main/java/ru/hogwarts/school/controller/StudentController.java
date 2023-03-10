package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);    // ?? метод
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
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

    @GetMapping
    public ResponseEntity<Collection<Student>> findByAgeBetween(
            @RequestParam(required = false) int min, int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }
}
//    @GetMapping
//    public ResponseEntity<Collection<Student>> findByAgeBetween(
//            @RequestParam(required = false) int min, int max,
//            @RequestParam(required = false) int age) {
//        if (age >= 0) {
//            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
//        }
//        if ((min != 0) && (max >= 0) && (max > min)) {
//            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
//        }
//        return ResponseEntity.notFound().build();
//    }
//}


