package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }
    public void deleteStudentId(Long id) {
        studentRepository.deleteById(id);
    }
    public Collection<Student> findAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max){
        return studentRepository.findByAgeBetween(min, max);
    }
//    public FacultyDTO findAllByFaculty_Id(Long id_faculty) {

}
